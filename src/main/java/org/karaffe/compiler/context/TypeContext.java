package org.karaffe.compiler.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NamedDef;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.Applying;
import org.karaffe.compiler.types.InferResult;

public class TypeContext {

    private final List<Package> allPackages = new ArrayList<>();
    private final List<Package> defaultImportPackages = new ArrayList<>();
    private final List<String> defaultImportClasses = new ArrayList<>();
    private final List<String> importedClasses = new ArrayList<>();
    private final Map<String, String> mappingRule = new HashMap<>();
    private final Map<Name, InferResult> availableDefs = new HashMap<>();

    private TypeContext() {
        // https://stackoverflow.com/questions/10993418/package-getpackage-in-java-returning-null
        List<String> packages = Arrays.asList(
                karaffe.core.Any.class.getPackage().getName(), // karaffe.core
                java.lang.Object.class.getPackage().getName(), // java.lang
                java.io.Closeable.class.getPackage().getName(), // java.io
                java.net.URL.class.getPackage().getName(), // java.net
                java.util.Collection.class.getPackage().getName(), // java.util
                java.time.LocalDateTime.class.getPackage().getName(), // java.time
                java.time.chrono.JapaneseEra.class.getPackage().getName()); // java.time.chrono
        packages.stream()
                .map(Package::getPackage)
                .map(Objects::requireNonNull)
                .forEach(this.defaultImportPackages::add);
        this.defaultImportClasses.addAll(Arrays.asList(
                java.math.BigInteger.class.getCanonicalName(),
                java.math.BigDecimal.class.getCanonicalName()));
        this.mappingRule.put(karaffe.core.Int.class.getSimpleName(), Integer.class.getCanonicalName());
        this.mappingRule.put(karaffe.core.Any.class.getSimpleName(), Object.class.getCanonicalName());
        Stream.of(Package.getPackages()).forEach(this.allPackages::add);
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
        // mayBeTypeName.ifPresent(t -> {
        // TypeName typeName = (TypeName) t;
        // Optional<List<String>> compatibleClasses =
        // TypeResolver.findAllCompatibleClasses(typeName.getText());
        // this.availableDefs.put(name,
        // compatibleClasses.map(InferResult::of).orElse(InferResult.noHint()));
        // });
        updateContext();
    }

    public boolean hasAlreadyDefinedName(Name name) {
        return this.availableDefs.containsKey(name);
    }

    public boolean hasAlreadyDefinedName(String name) {
        return this.hasAlreadyDefinedName(new Name(name));
    }

    public void updateType(Name name, InferResult result) {
        if (this.hasAlreadyDefinedName(name)) {
            this.availableDefs.put(name, result);
            updateContext();
        }
    }

    private void updateContext() {
        int lastUpdated = 0;
        int updated = updateContext1();
        while (lastUpdated != updated) {
            lastUpdated = updated;
            updated = updateContext1();
        }
    }

    private int updateType(Applying flatTarget, InferResult updatingType) {
        int updated = 0;
        List<Entry<Name, InferResult>> updating = this.availableDefs.entrySet().stream().filter(entry -> entry.getValue() == flatTarget).collect(Collectors.toList());
        for (Entry<Name, InferResult> entry : updating) {
            this.availableDefs.put(entry.getKey(), updatingType);
            updated++;
        }
        return updated;
    }

    private int updateContext1() {
        int updated = 0;
        return updated;
    }

    public Optional<InferResult> getInferredType(Name name) {
        InferResult inferResult = this.availableDefs.get(name);
        return Optional.ofNullable(inferResult);
    }

    public Optional<InferResult> getInferredType(String name) {
        InferResult inferResult = this.availableDefs.get(new Name(name));
        return Optional.ofNullable(inferResult);
    }

    public Optional<String> findFQCN(String simpleName) {
        return null;
    }

    public Optional<Package> findPackage(String packageName) {
        return this.allPackages
                .stream()
                .filter(pkg -> pkg.getName().equals(packageName))
                .findFirst();
    }

    public static TypeContext create() {
        return new TypeContext();
    }
}
