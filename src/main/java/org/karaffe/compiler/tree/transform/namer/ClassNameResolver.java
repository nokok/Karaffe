package org.karaffe.compiler.tree.transform.namer;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.karaffe.compiler.resolvers.TypeResolver;
import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.imports.AliasImport;
import org.karaffe.compiler.tree.v2.imports.OnDemandImport;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedTypeName;
import org.karaffe.compiler.tree.v2.names.PackageName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;

public class ClassNameResolver extends AbstractTransformer {

    public ClassNameResolver() {
        super("name-resolver");
    }

    private final Map<SimpleName, FullyQualifiedTypeName> simpleNameFqnMap = new HashMap<>();
    private final Map<TypeName, FullyQualifiedTypeName> typeNameFqnMap = new HashMap<>();

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
