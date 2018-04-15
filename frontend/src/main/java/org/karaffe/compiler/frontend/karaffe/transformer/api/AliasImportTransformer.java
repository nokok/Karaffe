package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.imports.AliasImport;

public interface AliasImportTransformer extends TypeNameTransformer {
    public default void onAliasImportBefore(AliasImport aliasImport) {

    }

    public default void onAliasImportAfter(AliasImport aliasImport) {

    }

    public default AliasImport transformBody(AliasImport oldAliasImport) {
        return new AliasImport(
                oldAliasImport.getPosition(),
                transform(oldAliasImport.getBefore()),
                transform(oldAliasImport.getAfter()));
    }

    public default AliasImport transform(AliasImport oldAliasImport) {
        onAliasImportBefore(oldAliasImport);
        AliasImport aliasImport = transformBody(oldAliasImport);
        onAliasImportAfter(aliasImport);
        return aliasImport;
    }
}
