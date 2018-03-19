package org.karaffe.compiler.backend.transformer.api;

import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.ast.PackageDef;
import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.api.ImportStatement;
import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.api.TypeDefMember;
import org.karaffe.compiler.ast.api.TypeDefStatement;

public interface BaseTransformer {

    public String getTransformerName();

    CompilationUnit transform(CompilationUnit oldCompilationUnit);

    PackageDef transform(PackageDef oldPackageDef);

    ImportStatement transform(ImportStatement oldImport);

    TypeDefStatement transform(TypeDefStatement typeDefStatement);

    TypeDefMember transform(TypeDefMember oldMember);

    Expression transform(Expression expression);

    Statement transform(Statement statement);

}