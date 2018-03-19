package org.karaffe.compiler.backend.transformer.api;

import org.karaffe.compiler.ast.imports.OnDemandImport;

public interface OnDemandImportTransformer extends PackageNameTransformer {
    public default void onOnDemandImportBefore(OnDemandImport onDemandImport) {

    }

    public default void onOnDemandImportAfter(OnDemandImport onDemandImport) {

    }

    public default OnDemandImport transform(OnDemandImport oldOnDemandImport) {
        onOnDemandImportBefore(oldOnDemandImport);
        OnDemandImport onDemandImport = transformBody(oldOnDemandImport);
        onOnDemandImportAfter(onDemandImport);
        return onDemandImport;
    }

    public default OnDemandImport transformBody(OnDemandImport oldOnDemandImport) {
        return new OnDemandImport(
                oldOnDemandImport.getPosition(),
                transform(oldOnDemandImport.getPackageName()));
    }
}
