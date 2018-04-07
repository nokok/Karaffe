package org.karaffe.compiler.transformer.api;

import org.karaffe.compiler.ast.api.ImportStatement;
import org.karaffe.compiler.ast.imports.SimpleImport;

public interface SimpleImportTransformer extends FullyQualifiedNameTransformer {

    public default void onSimpleImportBefore(SimpleImport simpleImport) {

    }

    public default void onSimpleImportAfter(SimpleImport simpleImport) {

    }

    public default SimpleImport transformBody(SimpleImport oldImport) {
        return new SimpleImport(oldImport.getPosition(), transform(oldImport.getName()));
    }

    public default ImportStatement transform(SimpleImport oldImport) {
        onSimpleImportBefore(oldImport);
        SimpleImport simpleImport = transformBody(oldImport);
        onSimpleImportAfter(simpleImport);
        return simpleImport;
    }
}