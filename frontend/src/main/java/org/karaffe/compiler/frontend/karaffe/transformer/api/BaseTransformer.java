package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;
import org.karaffe.compiler.frontend.karaffe.ast.ModuleDef;
import org.karaffe.compiler.frontend.karaffe.ast.PackageDef;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefMember;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;

public interface BaseTransformer {

    public String getTransformerName();

    CompilationUnit transform(CompilationUnit oldCompilationUnit);

    ModuleDef transform(ModuleDef oldModuleDef);

    PackageDef transform(PackageDef oldPackageDef);

    ImportStatement transform(ImportStatement oldImport);

    TypeDefStatement transform(TypeDefStatement typeDefStatement);

    TypeDefMember transform(TypeDefMember oldMember);

    Expression transform(Expression expression);

    Statement transform(Statement statement);

}