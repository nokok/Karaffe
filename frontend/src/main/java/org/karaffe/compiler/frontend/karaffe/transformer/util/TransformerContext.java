package org.karaffe.compiler.frontend.karaffe.transformer.util;

import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public enum TransformerContext {
    INSTANCE,;

    private final Map<String, List<ImportStatement>> fileImportMap = new HashMap<>();

    public void addImport(String fileName, ImportStatement stmt) {
        List<ImportStatement> s;
        if (this.fileImportMap.containsKey(fileName)) {
            s = this.fileImportMap.get(fileName);
        } else {
            s = new ArrayList<>();
        }
        s.add(stmt);
        this.fileImportMap.put(fileName, s);
    }
}
