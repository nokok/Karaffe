package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;
import org.karaffe.compiler.frontend.karaffe.ast.imports.AliasImport;
import org.karaffe.compiler.frontend.karaffe.ast.imports.OnDemandImport;
import org.karaffe.compiler.frontend.karaffe.ast.imports.SimpleImport;

public interface ImportStatementTransformer extends
        BaseTransformer,
        SimpleImportTransformer,
        OnDemandImportTransformer,
        AliasImportTransformer {

    @Override
    public default ImportStatement transform(ImportStatement oldImport) {
        switch (oldImport.getStatementType()) {
        case SIMPLE_IMPORT_DEF:
            return transform((SimpleImport) oldImport);
        case ONDEMAND_IMPORT_DEF:
            return transform((OnDemandImport) oldImport);
        case ALIAS_IMPORT:
            return transform((AliasImport) oldImport);
        default:
            throw new UnsupportedOperationException(oldImport.getStatementType().name());
        }
    }

}
