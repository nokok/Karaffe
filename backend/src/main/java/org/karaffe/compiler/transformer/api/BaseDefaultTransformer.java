package org.karaffe.compiler.transformer.api;

public interface BaseDefaultTransformer
        extends
        CompilationUnitTransformer,
        PackageDefTransformer,
        ImportStatementTransformer,
        TypeDefStmtTransformer,
        ExpressionTransformer,
        StatementTransformer {

}
