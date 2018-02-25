package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.imports.OnDemandImport;

public interface OnDemandImportTransformer {
    public default void onOnDemandImportBefore(OnDemandImport onDemandImport) {

    }

    public default void onOnDemandImportAfter(OnDemandImport onDemandImport) {

    }

    public default OnDemandImport transform(OnDemandImport oldOnDemandImport) {
        onOnDemandImportAfter(oldOnDemandImport);
        OnDemandImport onDemandImport = new OnDemandImport(oldOnDemandImport.getPackageName());
        onOnDemandImportAfter(onDemandImport);
        return onDemandImport;
    }
}
