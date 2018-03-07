package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.names.PackageName;

public interface PackageNameTransformer {

    public default void onPackageNameBefore(PackageName packageName) {

    }

    public default void onPackageNameAfter(PackageName packageName) {

    }

    public default PackageName transform(PackageName oldPackageName) {
        onPackageNameBefore(oldPackageName);
        PackageName packageName = transformBody(oldPackageName);
        onPackageNameAfter(packageName);
        return packageName;
    }

    public default PackageName transformBody(PackageName packageName) {
        return new PackageName(packageName);
    }
}
