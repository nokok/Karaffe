package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.PackageDef;

public interface PackageDefTransformListener {

    public default void onPackageDefBefore(PackageDef packageDef) {

    }

    public default void onPackageDefAfter(PackageDef packageDef) {

    }

}