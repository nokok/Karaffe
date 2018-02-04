package org.karaffe.compiler.context;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NamedDef;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.Applying;
import org.karaffe.compiler.types.InferResult;
import org.karaffe.compiler.types.InferResult.ResultType;
import org.karaffe.compiler.types.MayBeApplicable;
import org.karaffe.compiler.types.MethodResolver;
import org.karaffe.compiler.types.TypeResolver;

import karaffe.core.Unit;

public class TypeInferenceContext {

    private final List<Package> defaultImportPackages = new ArrayList<>();
    private final List<String> defaultImportClasses = new ArrayList<>();
    private final List<String> importedClasses = new ArrayList<>();
    private final Map<String, String> mappingRule = new HashMap<>();
    private final Map<Name, InferResult> availableDefs = new HashMap<>();
    private final Map<Name, InferResult> builtInFunctions = new HashMap<>();

    private TypeInferenceContext() {
        // https://stackoverflow.com/questions/10993418/package-getpackage-in-java-returning-null
        List<String> packages = Arrays.asList(
                java.lang.Object.class.getPackage().getName(), // java.lang
                java.io.Closeable.class.getPackage().getName(), // java.io
                java.net.URL.class.getPackage().getName(), // java.net
                java.util.Collection.class.getPackage().getName(), // java.util
                java.time.LocalDateTime.class.getPackage().getName(), // java.time
                java.time.chrono.JapaneseEra.class.getPackage().getName(), // java.time.chrono
                karaffe.core.Any.class.getPackage().getName()); // karaffe.core
        packages.stream()
                .map(Package::getPackage)
                .map(Objects::requireNonNull)
                .forEach(this.defaultImportPackages::add);
        this.defaultImportClasses.addAll(Arrays.asList(
                java.math.BigInteger.class.getCanonicalName(),
                java.math.BigDecimal.class.getCanonicalName()));
        this.mappingRule.put(karaffe.core.Int.class.getSimpleName(), Integer.class.getCanonicalName());
        this.mappingRule.put(karaffe.core.Any.class.getSimpleName(), Object.class.getCanonicalName());
        this.builtInFunctions.put(new Name("println"), InferResult.of(Unit.class));
    }

    public void addImport(String importName) {
        this.importedClasses.add(Objects.requireNonNull(importName));
        updateContext();
    }

    public void addDef(NamedDef def) {
        Name name = def.findNameNode();
        Optional<Node> mayBeTypeName = def.findDefinitionTypeName();
        Optional<Node> mayBeInitializer = def.findInitializer();
        if (!mayBeTypeName.isPresent() && !mayBeInitializer.isPresent()) {
            this.availableDefs.put(def.findNameNode(), InferResult.noHint());
            return;
        }
        mayBeInitializer.ifPresent(initializer -> {
            Optional<InferResult> inferResult = initializer.tryTypeInference(this);
            inferResult.ifPresent(inferRes -> {
                this.availableDefs.put(name, inferRes);
            });
        });
        mayBeTypeName.ifPresent(t -> {
            TypeName typeName = (TypeName) t;
            Optional<List<String>> compatibleClasses = TypeResolver.findAllCompatibleClasses(typeName.getText());
            this.availableDefs.put(name, compatibleClasses.map(InferResult::of).orElse(InferResult.noHint()));
        });
        updateContext();
    }

    public boolean hasAlreadyDefinedName(Name name) {
        return this.availableDefs.containsKey(name);
    }

    public void updateType(Name name, InferResult result) {
        if (this.hasAlreadyDefinedName(name)) {
            this.availableDefs.put(name, result);
            updateContext();
        }
    }

    private void updateContext() {
        List<Applying> applyings = this.availableDefs
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getInferResultType() == ResultType.APPLYING)
                .map(Map.Entry::getValue)
                .map(Applying.class::cast)
                .collect(Collectors.toList());
        for (Applying applying : applyings) {
            MayBeApplicable applicable = applying.getApplicable();
            Name methodName = applicable.getMemberName();
            String typeName = applicable.getOwnerType().getType().get();
            Class<?> ownerClass = TypeResolver.findClass(typeName).get();
            List<Method> methods = new MethodResolver(ownerClass).findMethods(methodName);
            List<Class<?>> args = applying.getArgs().stream()
                    .map(InferResult::getType)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(TypeResolver::findClass)
                    .filter(Optional::isPresent)
                    .<Class<?>>map(Optional::get)
                    .collect(Collectors.toList());
            List<Method> methods2 = methods.stream()
                    .filter(method -> method.getParameterTypes().length == args.size())
                    .collect(Collectors.toList());
            if (methods2.size() == 1) {
                InferResult returnType = InferResult.of(methods2.get(0).getReturnType().getCanonicalName());
                List<Entry<Name, InferResult>> updating = this.availableDefs.entrySet().stream().filter(entry -> entry.getValue() == applying).collect(Collectors.toList());
                for (Entry<Name, InferResult> entry : updating) {
                    this.availableDefs.put(entry.getKey(), returnType);
                }
            }
        }
    }

    public Optional<InferResult> getInferredType(Name name) {
        InferResult inferResult = this.availableDefs.get(name);
        return Optional.ofNullable(inferResult);
    }

    public Optional<String> findFQCN(String simpleName) {
        Objects.requireNonNull(simpleName);
        Optional<String> packageSearch = this.defaultImportPackages.stream().map(pkg -> pkg.getName() + "." + simpleName).filter(TypeResolver::isValidFQCN).findFirst();
        Optional<String> classSearch = this.defaultImportClasses.stream().filter(fqcn -> fqcn.endsWith("." + simpleName)).findFirst();
        if (classSearch.isPresent()) {
            return classSearch;
        }
        return packageSearch;
    }

    public static TypeInferenceContext create() {
        return new TypeInferenceContext();
    }
}
