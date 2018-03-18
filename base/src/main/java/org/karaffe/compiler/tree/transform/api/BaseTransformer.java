package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.TypeDefMember;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;

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