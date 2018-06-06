package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.Def;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.Type;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultVisitor<P> implements TreeVisitor<Tree, P> {

    private List<Tree> visitChildren(Tree t, P p) {
        return t.getChildren().stream().map(c -> c.accept(this, p)).collect(Collectors.toList());
    }

    private List<Tree> visitModifiers(Tree t, P p) {
        return t.getModifiers().stream().map(m -> m.accept(this, p)).collect(Collectors.toList());
    }

    private Tree visitTree(Tree tree, P p) {
        tree.setType(tree.asType().accept(this, p));
        tree.setChildren(visitChildren(tree, p));
        tree.setModifiers(visitModifiers(tree, p));
        tree.setName(tree.getName().accept(this, p));
        return tree;
    }

    @Override
    public Tree visitLetDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitAssignmentDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitClassDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitImportDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitMethodDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPackageDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitNoneName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitFQCN(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPackageName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitThisName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitVarName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitTypeName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Modifier tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitStaticMod(Modifier tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPublicMod(Modifier tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitRefType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitArrayType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveIntType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveCharType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveByteType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitVoidType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveBooleanType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveLongType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveFloatType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveShortType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveDoubleType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitNoType(Type tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitCompileUnit(Tree.CompilationUnit tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitTemplate(Tree.Template tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitApply(Apply tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitAtom(Atom tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitBlock(Block tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitSelect(Select tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitOperator(Operator tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitTuple(Tuple tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitIfExpr(IfExpr tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitWhileExpr(WhileExpr tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitEmpty(EmptyTree tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitCast(Cast tree, P p) {
        return visitTree(tree, p);
    }
}
