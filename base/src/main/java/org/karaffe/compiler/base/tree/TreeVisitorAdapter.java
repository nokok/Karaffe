package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Atom;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.Operator;
import org.karaffe.compiler.base.tree.expr.Select;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.Path;

public class TreeVisitorAdapter<R, P> implements TreeVisitor<R, P> {

    @Override
    public R visitLetDef(LetDef simpleDef, P p) {
        return null;
    }

    @Override
    public R visitAssignmentDef(AssignmentDef simpleDef, P p) {
        return null;
    }

    @Override
    public R visitClassDef(ClassDef def, P p) {
        return null;
    }

    @Override
    public R visitSimpleImportDef(SimpleImport def, P p) {
        return null;
    }

    @Override
    public R visitOnDemandImportDef(OnDemandImport onDemandImport, P p) {
        return null;
    }

    @Override
    public R visitMethodDef(MethodDef def, P p) {
        return null;
    }

    @Override
    public R visitPackageDef(PackageDef def, P p) {
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
    public R visitSyntheticMod(Modifier simpleModifier, P p) {
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
    public Path visitOperator(Operator operator, P p) {
        return null;
    }

    @Override
    public Path visitModuleName(Path moduleName, P p) {
        return null;
    }

    @Override
    public Path visitPackageName(Path packageName, P p) {
        return null;
    }

    @Override
    public Path visitEmptyName(Path emptyPath, P p) {
        return null;
    }

    @Override
    public Path visitTypeName(Path path, P p) {
        return null;
    }

    @Override
    public Path visitVarName(Path path, P p) {
        return null;
    }

    @Override
    public Path visitThisName(Path path, P p) {
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

    @Override
    public R visitBinding(Binding binding, P p) {
        return null;
    }


}
