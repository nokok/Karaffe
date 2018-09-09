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
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.stmt.ReturnStatement;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.NameNode;
import org.karaffe.compiler.base.tree.term.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeVisitorAdapter<R, P> implements TreeVisitor<R, P> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeVisitorAdapter.class);

    @Override
    public R visit(LetDef simpleDef, P p) {
        LOGGER.trace("visit", simpleDef);
        return null;
    }

    @Override
    public R visit(AssignmentDef simpleDef, P p) {
        LOGGER.trace("visit", simpleDef);
        return null;
    }

    @Override
    public R visit(ClassDef def, P p) {
        LOGGER.trace("visit", def);
        return null;
    }

    @Override
    public R visit(SimpleImport def, P p) {
        LOGGER.trace("visit", def);
        return null;
    }

    @Override
    public R visit(OnDemandImport onDemandImport, P p) {
        LOGGER.trace("visit", onDemandImport);
        return null;
    }

    @Override
    public R visit(MethodDef def, P p) {
        LOGGER.trace("visit", def);
        return null;
    }

    @Override
    public R visit(PackageDef def, P p) {
        LOGGER.trace("visit", def);
        return null;
    }

    @Override
    public R visitStaticMod(Modifier modifier, P p) {
        LOGGER.trace("visitStaticMod", modifier);
        return null;
    }

    @Override
    public R visitPublicMod(Modifier modifier, P p) {
        LOGGER.trace("visitPublicMod", modifier);
        return null;
    }

    @Override
    public R visitSyntheticMod(Modifier simpleModifier, P p) {
        LOGGER.trace("visitSyntheticMod", simpleModifier);
        return null;
    }

    @Override
    public R visit(Tree.CompilationUnit tree, P p) {
        LOGGER.trace("visit", tree);
        return null;
    }

    @Override
    public R visit(Tree.Template template, P p) {
        LOGGER.trace("visit", template);
        return null;
    }

    @Override
    public R visit(Apply apply, P p) {
        LOGGER.trace("visit", apply);
        return null;
    }

    @Override
    public R visit(Atom atom, P p) {
        LOGGER.trace("visit", atom);
        return null;
    }

    @Override
    public R visit(Block block, P p) {
        LOGGER.trace("visit", block);
        return null;
    }

    @Override
    public Path visitOperator(Operator operator, P p) {
        LOGGER.trace("visitOperator", operator);
        return null;
    }

    @Override
    public Path visitModuleName(Path moduleName, P p) {
        LOGGER.trace("visitModuleName", moduleName);
        return null;
    }

    @Override
    public Path visitPackageName(Path packageName, P p) {
        LOGGER.trace("visitPackageName", packageName);
        return null;
    }

    @Override
    public Path visitEmptyName(Path emptyPath, P p) {
        LOGGER.trace("visitEmptyName", emptyPath);
        return null;
    }

    @Override
    public Path visitTypeName(Path path, P p) {
        LOGGER.trace("visitTypeName", path);
        return null;
    }

    @Override
    public Path visitVarName(Path path, P p) {
        LOGGER.trace("visitVarName", path);
        return null;
    }

    @Override
    public Path visitThisName(Path path, P p) {
        LOGGER.trace("visitThisName", path);
        return null;
    }

    @Override
    public R visit(Tuple tuple, P p) {
        LOGGER.trace("visit", tuple);
        return null;
    }

    @Override
    public R visit(IfExpr ifExpr, P p) {
        LOGGER.trace("visit", ifExpr);
        return null;
    }

    @Override
    public R visit(WhileExpr whileExpr, P p) {
        LOGGER.trace("visit", whileExpr);
        return null;
    }

    @Override
    public R visit(EmptyTree emptyTree, P p) {
        LOGGER.trace("visit", emptyTree);
        return null;
    }

    @Override
    public R visit(Cast cast, P p) {
        LOGGER.trace("visit", cast);
        return null;
    }

    @Override
    public R visit(Binding binding, P p) {
        LOGGER.trace("visit", binding);
        return null;
    }

    @Override
    public R visit(ReturnStatement returnStatement, P p) {
        LOGGER.trace("visit", returnStatement);
        return null;
    }

    @Override
    public R visit(NameNode nameNode, P p) {
        LOGGER.trace("visit");
        return null;
    }


}
