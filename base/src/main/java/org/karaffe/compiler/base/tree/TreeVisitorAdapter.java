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

public class TreeVisitorAdapter<R, P> implements TreeVisitor<R, P> {

    @Override
    public R visitLetDef(Def simpleDef, P p) {
        return null;
    }

    @Override
    public R visitAssignmentDef(Def simpleDef, P p) {
        return null;
    }

    @Override
    public R visitClassDef(Def def, P p) {
        return null;
    }

    @Override
    public R visitImportDef(Def def, P p) {
        return null;
    }

    @Override
    public R visitMethodDef(Def def, P p) {
        return null;
    }

    @Override
    public R visitPackageDef(Def def, P p) {
        return null;
    }

    @Override
    public R visitNoneName(Name name, P p) {
        return null;
    }

    @Override
    public R visitFQCN(Name name, P p) {
        return null;
    }

    @Override
    public R visitPackageName(Name name, P p) {
        return null;
    }

    @Override
    public R visitThisName(Name name, P p) {
        return null;
    }

    @Override
    public R visitVarName(Name name, P p) {
        return null;
    }

    @Override
    public R visitTypeName(Name name, P p) {
        return null;
    }

    @Override
    public R visit(Modifier modifier, P p) {
        return null;
    }

    @Override
    public R visitStaticMod(Modifier modifier, P p) {
        return null;
    }

    @Override
    public R visitPublicMod(Modifier modifier, P p) {
        return null;
    }

    @Override
    public R visitRefType(Type type, P p) {
        return null;
    }

    @Override
    public R visitArrayType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveIntType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveCharType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveByteType(Type type, P p) {
        return null;
    }

    @Override
    public R visitVoidType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveBooleanType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveLongType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveFloatType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveShortType(Type type, P p) {
        return null;
    }

    @Override
    public R visitPrimitiveDoubleType(Type type, P p) {
        return null;
    }

    @Override
    public R visitNoType(Type type, P p) {
        return null;
    }

    @Override
    public R visitCompileUnit(Tree.CompilationUnit tree, P p) {
        return null;
    }

    @Override
    public R visitTemplate(Tree.Template template, P p) {
        return null;
    }

    @Override
    public R visitApply(Apply apply, P p) {
        return null;
    }

    @Override
    public R visitAtom(Atom atom, P p) {
        return null;
    }

    @Override
    public R visitBlock(Block block, P p) {
        return null;
    }

    @Override
    public R visitSelect(Select select, P p) {
        return null;
    }

    @Override
    public R visitOperator(Operator operator, P p) {
        return null;
    }

    @Override
    public R visitTuple(Tuple tuple, P p) {
        return null;
    }

    @Override
    public R visitIfExpr(IfExpr ifExpr, P p) {
        return null;
    }

    @Override
    public R visitWhileExpr(WhileExpr whileExpr, P p) {
        return null;
    }

    @Override
    public R visitEmpty(EmptyTree emptyTree, P p) {
        return null;
    }

    @Override
    public R visitCast(Cast cast, P p) {
        return null;
    }


}
