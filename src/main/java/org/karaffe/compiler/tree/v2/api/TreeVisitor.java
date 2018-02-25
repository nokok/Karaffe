package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.Parameter;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.Plus;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.modifiers.Public;
import org.karaffe.compiler.tree.v2.modifiers.Static;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedName;
import org.karaffe.compiler.tree.v2.names.PackageName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;
import org.karaffe.compiler.tree.v2.statements.ClassDef;
import org.karaffe.compiler.tree.v2.statements.InterfaceDef;
import org.karaffe.compiler.tree.v2.statements.LetFieldDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

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

    public void visit(FullyQualifiedName name);

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
