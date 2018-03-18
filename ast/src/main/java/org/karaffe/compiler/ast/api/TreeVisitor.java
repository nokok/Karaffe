package org.karaffe.compiler.ast.api;

import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.ast.PackageDef;
import org.karaffe.compiler.ast.Parameter;
import org.karaffe.compiler.ast.expressions.Apply;
import org.karaffe.compiler.ast.expressions.Block;
import org.karaffe.compiler.ast.expressions.ExpressionName;
import org.karaffe.compiler.ast.expressions.IntLiteral;
import org.karaffe.compiler.ast.expressions.Plus;
import org.karaffe.compiler.ast.imports.SimpleImport;
import org.karaffe.compiler.ast.modifiers.Public;
import org.karaffe.compiler.ast.modifiers.Static;
import org.karaffe.compiler.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.ast.names.PackageName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.ast.statements.ClassDef;
import org.karaffe.compiler.ast.statements.InterfaceDef;
import org.karaffe.compiler.ast.statements.LetFieldDef;
import org.karaffe.compiler.ast.statements.LetLocalDef;
import org.karaffe.compiler.ast.statements.MethodDef;

public interface TreeVisitor {
    public void visit(CompilationUnit compilationUnit);

    public void visit(PackageDef packageDef);

    public void visit(Parameter parameter);

    public void visit(Apply apply);

    public void visit(ExpressionName expressionName);

    public void visit(IntLiteral intLiteral);

    public void visit(Plus plus);

    public void visit(SimpleImport simpleImport);

    public void visit(Public pub);

    public void visit(Static st);

    public void visit(FullyQualifiedTypeName name);

    public void visit(PackageName packageName);

    public void visit(SimpleName simpleName);

    public void visit(TypeName typeName);

    public void visit(ClassDef classDef);

    public void visit(InterfaceDef interfaceDef);

    public void visit(LetFieldDef letFieldDef);

    public void visit(LetLocalDef localLetDef);

    public void visit(MethodDef methodDef);

    public void visit(Block block);
}
