package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.imports.AliasImport;

public interface AliasImportTransformer extends TypeNameTransformer {
    public default void onAliasImportBefore(AliasImport aliasImport) {

    }

    public default void onAliasImportAfter(AliasImport aliasImport) {

    }

    public default AliasImport transform(AliasImport oldAliasImport) {
        onAliasImportBefore(oldAliasImport);
        AliasImport aliasImport = new AliasImport(transform(oldAliasImport.getBefore()), transform(oldAliasImport.getAfter()));
        onAliasImportAfter(aliasImport);
        return aliasImport;
    }
}
