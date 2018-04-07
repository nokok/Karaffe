package org.karaffe.compiler.transformer;

import org.karaffe.compiler.ast.PackageDef;
import org.karaffe.compiler.ast.api.TypeDefStatement;
import org.karaffe.compiler.ast.imports.AliasImport;
import org.karaffe.compiler.ast.imports.OnDemandImport;
import org.karaffe.compiler.ast.imports.SimpleImport;
import org.karaffe.compiler.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.ast.names.PackageName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.backend.jvm.resolvers.TypeResolver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class NameResolver extends AbstractTransformer {

    public NameResolver() {
        super("name-resolver");
    }

    private final Map<SimpleName, FullyQualifiedTypeName> simpleNameFqnMap = new LinkedHashMap<>();
    private final Map<TypeName, FullyQualifiedTypeName> typeNameFqnMap = new LinkedHashMap<>();

    @Override
    public void onPackageDefBefore(PackageDef packageDef) {
        this.simpleNameFqnMap.clear();
        this.typeNameFqnMap.clear();
    }

    @Override
    public void onSimpleImportAfter(SimpleImport simpleImport) {
        this.simpleNameFqnMap.put(simpleImport.getImportedSimpleName(), simpleImport.getName());
    }

    @Override
    public void onOnDemandImportAfter(OnDemandImport onDemandImport) {
        PackageName packageName = onDemandImport.getPackageName();
        Package pkg = Package.getPackage(packageName.toString());
        if (pkg == null) {
            throw new RuntimeException("No package name found. : " + packageName);
        }
        TypeResolver.findClassesInPackage(packageName).ifPresent(classes -> {
            classes.forEach(clazz -> {
                this.simpleNameFqnMap.put(new SimpleName(clazz.getSimpleName()), new FullyQualifiedTypeName(clazz));
            });
        });
    }

    @Override
    public void onAliasImportAfter(AliasImport aliasImport) {
        this.typeNameFqnMap.put(aliasImport.getAfter(), aliasImport.getBefore());
    }

    @Override
    public TypeName transformBody(TypeName typeName) {
        Optional<FullyQualifiedTypeName> fqn = getFqn(typeName);
        if (fqn.isPresent()) {
            return fqn.get();
        }
        return typeName;
    }

    @Override
    public SimpleName transformOnTypeDefSuperClass(TypeDefStatement parent, SimpleName superClass) {
        Optional<FullyQualifiedTypeName> fqn = getFqn(superClass);
        if (fqn.isPresent()) {
            return fqn.get();
        }
        return superClass;
    }

    @Override
    public List<? extends SimpleName> transformOnTypeDefInterfaces(TypeDefStatement parent, List<? extends SimpleName> oldInterfaces) {
        return oldInterfaces.stream().map(name -> {
            Optional<FullyQualifiedTypeName> fqn = getFqn(name);
            if (fqn.isPresent()) {
                return fqn.get();
            }
            return name;
        }).collect(Collectors.toList());
    }

    private Optional<FullyQualifiedTypeName> getFqn(SimpleName simpleName) {
        return Optional.ofNullable(Optional.ofNullable(this.simpleNameFqnMap.get(simpleName)).orElse(this.typeNameFqnMap.get(new TypeName(simpleName))));
    }

    private Optional<FullyQualifiedTypeName> getFqn(TypeName typeName) {
        if (typeName.isFullyQualified()) {
            return Optional.empty();
        }
        return Optional.ofNullable(
                Optional.ofNullable(this.simpleNameFqnMap.get(typeName.getName()))
                        .orElse(this.typeNameFqnMap.get(typeName)));
    }

}
