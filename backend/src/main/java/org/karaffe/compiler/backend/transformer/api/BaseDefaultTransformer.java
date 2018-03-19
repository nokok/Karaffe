package org.karaffe.compiler.backend.transformer.api;

public interface BaseDefaultTransformer
        extends
        CompilationUnitTransformer,
        PackageDefTransformer,
        ImportStatementTransformer,
        TypeDefStmtTransformer,
        ExpressionTransformer,
        StatementTransformer {

}
