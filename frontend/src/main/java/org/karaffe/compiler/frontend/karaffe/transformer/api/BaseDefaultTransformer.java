package org.karaffe.compiler.frontend.karaffe.transformer.api;

public interface BaseDefaultTransformer
        extends
        CompilationUnitTransformer,
        PackageDefTransformer,
        ImportStatementTransformer,
        TypeDefStmtTransformer,
        ExpressionTransformer,
        StatementTransformer {

}
