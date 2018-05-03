package org.karaffe.compiler.frontend.karaffe.ast;

import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompilationUnit extends AbstractTree {

    private final Map<String, List<TypeDefStatement>> fileTypesMap = new HashMap<>();
    private String fileName = "";
    private List<TypeDefStatement> types = new ArrayList<>();

    public void clearFileName() {
        this.fileName = "";
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void addTypeDefStatement(TypeDefStatement typeDefStatement) {
        this.types.add(typeDefStatement);
        if (!this.fileName.isEmpty()) {
            this.fileTypesMap.put(this.fileName, this.types);
        }
    }

    public List<TypeDefStatement> getTypeDefStatements() {
        return Collections.unmodifiableList(this.types);
    }

    public void setTypeDefStatements(List<TypeDefStatement> types) {
        this.types = types;
        if (!this.fileName.isEmpty()) {
            this.fileTypesMap.put(this.fileName, this.types);
        }
    }

    public CompilationUnit merge(CompilationUnit that) {
        CompilationUnit merged = new CompilationUnit();
        this.fileTypesMap.forEach(merged.fileTypesMap::put);
        that.fileTypesMap.forEach(merged.fileTypesMap::put);
        merged.setFileName(this.fileName + ", " + that.fileName);
        merged.types.addAll(this.types);
        merged.types.addAll(that.types);
        return merged;
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("/* Compilation Unit */ {");
        if (!this.fileTypesMap.isEmpty()) {
            lines.add("FileTypesMap = [");
            this.fileTypesMap.forEach((k, v) -> {
                lines.add("  " + k + " -> " + v.stream().map(TypeDefStatement::getName).map(SimpleName::toString).reduce((l, r) -> l + "," + r).orElse(""));
            });
            lines.add("]");
        }
        this.types.stream().map(TypeDefStatement::toString).forEach(lines::add);
        lines.add("}");
        return String.join("\n", lines);
    }
}
