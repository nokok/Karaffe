package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.PackageDef;

import java.util.stream.Collectors;

public interface PackageDefTransformer extends BaseTransformer, PackageNameTransformer {

    public default void onPackageDefBefore(PackageDef packageDef) {

    }

    public default void onPackageDefAfter(PackageDef packageDef) {

    }

    @Override
    public default PackageDef transform(PackageDef oldPackageDef) {
        onPackageDefBefore(oldPackageDef);
        PackageDef packageDef = transformBody(oldPackageDef);
        onPackageDefAfter(packageDef);
        return packageDef;
    }

    public default PackageDef transformBody(PackageDef oldPackageDef) {
        return new PackageDef(
                oldPackageDef.getPosition(),
                transform(oldPackageDef.getPackageName()),
                oldPackageDef.getImports().stream().map(this::transform).collect(Collectors.toList()),
                oldPackageDef.getTypeDefStatements().stream().map(this::transform).collect(Collectors.toList()));
    }
}
