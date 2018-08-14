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
    public R visitLetDef(LetDef simpleDef, P p) {
        LOGGER.trace("visitLetDef", simpleDef);
        return null;
    }

    @Override
    public R visitAssignmentDef(AssignmentDef simpleDef, P p) {
        LOGGER.trace("visitAssignmentDef", simpleDef);
        return null;
    }

    @Override
    public R visitClassDef(ClassDef def, P p) {
        LOGGER.trace("visitClassDef", def);
        return null;
    }

    @Override
    public R visitSimpleImportDef(SimpleImport def, P p) {
        LOGGER.trace("visitSimpleImportDef", def);
        return null;
    }

    @Override
    public R visitOnDemandImportDef(OnDemandImport onDemandImport, P p) {
        LOGGER.trace("visitOnDemandImportDef", onDemandImport);
        return null;
    }

    @Override
    public R visitMethodDef(MethodDef def, P p) {
        LOGGER.trace("visitMethodDef", def);
        return null;
    }

    @Override
    public R visitPackageDef(PackageDef def, P p) {
        LOGGER.trace("visitPackageDef", def);
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
    public R visitCompileUnit(Tree.CompilationUnit tree, P p) {
        LOGGER.trace("visitCompileUnit", tree);
        return null;
    }

    @Override
    public R visitTemplate(Tree.Template template, P p) {
        LOGGER.trace("visitTemplate", template);
        return null;
    }

    @Override
    public R visitApply(Apply apply, P p) {
        LOGGER.trace("visitApply", apply);
        return null;
    }

    @Override
    public R visitAtom(Atom atom, P p) {
        LOGGER.trace("visitAtom", atom);
        return null;
    }

    @Override
    public R visitBlock(Block block, P p) {
        LOGGER.trace("visitBlock", block);
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
    public R visitTuple(Tuple tuple, P p) {
        LOGGER.trace("visitTuple", tuple);
        return null;
    }

    @Override
    public R visitIfExpr(IfExpr ifExpr, P p) {
        LOGGER.trace("visitIfExpr", ifExpr);
        return null;
    }

    @Override
    public R visitWhileExpr(WhileExpr whileExpr, P p) {
        LOGGER.trace("visitWhileExpr", whileExpr);
        return null;
    }

    @Override
    public R visitEmpty(EmptyTree emptyTree, P p) {
        LOGGER.trace("visitEmpty", emptyTree);
        return null;
    }

    @Override
    public R visitCast(Cast cast, P p) {
        LOGGER.trace("visitCast", cast);
        return null;
    }

    @Override
    public R visitBinding(Binding binding, P p) {
        LOGGER.trace("visitBinding", binding);
        return null;
    }

    @Override
    public R visitReturn(ReturnStatement returnStatement, P p) {
        LOGGER.trace("visitReturn", returnStatement);
        return null;
    }

    @Override
    public R visitNameNode(NameNode nameNode, P p) {
        LOGGER.trace("visitNameNode");
        return null;
    }


}
