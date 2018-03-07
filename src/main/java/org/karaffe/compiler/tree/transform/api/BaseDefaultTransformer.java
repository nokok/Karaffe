package org.karaffe.compiler.tree.transform.api;

public interface BaseDefaultTransformer
        extends
        CompilationUnitTransformer,
        PackageDefTransformer,
        ImportStatementTransformer,
        TypeDefStmtTransformer,
        ExpressionTransformer,
        StatementTransformer {

}
