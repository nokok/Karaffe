package org.karaffe.compiler.transformer.util;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.statements.ClassDef;
import org.karaffe.compiler.base.util.Report;
import org.karaffe.compiler.transformer.type.TypeEnv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum TransformerContext {
    CONTEXT,;

    private final TypeEnv typeEnv = new TypeEnv();
    private final List<Report> reports = new ArrayList<>();

    private final Map<ClassDef, String> sourceFileMap = new HashMap<>();

    public void addReport(Report report) {
        this.reports.add(report);
    }

    public void addExprState(Expression expression) {
        this.typeEnv.addExpr(expression);
    }

    public String getSourceFile(ClassDef classDef) {
        return Optional.ofNullable(this.sourceFileMap.get(classDef)).orElse("");
    }
}
