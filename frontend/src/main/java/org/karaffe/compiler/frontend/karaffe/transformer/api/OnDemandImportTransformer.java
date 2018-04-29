package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.imports.OnDemandImport;

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
