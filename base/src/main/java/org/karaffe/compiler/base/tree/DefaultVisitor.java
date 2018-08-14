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

import java.util.List;
import java.util.stream.Collectors;

public class DefaultVisitor<P> implements TreeVisitor<Tree, P> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultVisitor.class);

    private List<Tree> visitChildren(Tree t, P p) {
        return t.getChildren().stream().map(c -> c.accept(this, p)).collect(Collectors.toList());
    }

    private List<Tree> visitModifiers(Tree t, P p) {
        return t.getModifiers().stream().map(m -> m.accept(this, p)).collect(Collectors.toList());
    }

    private Tree visitTree(Tree tree, P p) {
        Path name = tree.getName().accept(this, p);
        Path typeName = tree.getTypeName().accept(this, p);
        List<Tree> trees = visitChildren(tree, p);
        List<Tree> modifiers = visitModifiers(tree, p);
        tree.setName(name);
        tree.setTypeName(typeName);
        tree.setChildren(trees);
        tree.setModifiers(modifiers);
        return tree;
    }

    @Override
    public Tree visitLetDef(LetDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitAssignmentDef(AssignmentDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitClassDef(ClassDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitSimpleImportDef(SimpleImport tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitOnDemandImportDef(OnDemandImport tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitMethodDef(MethodDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPackageDef(PackageDef tree, P p) {
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
    public Tree visitSyntheticMod(Modifier tree, P p) {
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
    public Path visitOperator(Operator tree, P p) {
        return tree;
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

    @Override
    public Tree visitBinding(Binding tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitReturn(ReturnStatement tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitNameNode(NameNode tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Path visitModuleName(Path path, P p) {
        return path;
    }

    @Override
    public Path visitPackageName(Path path, P p) {
        return path;
    }

    @Override
    public Path visitEmptyName(Path path, P p) {
        return path;
    }

    @Override
    public Path visitTypeName(Path path, P p) {
        LOGGER.trace("TypeName: {}", path);
        return path;
    }

    @Override
    public Path visitVarName(Path path, P p) {
        return path;
    }

    @Override
    public Path visitThisName(Path path, P p) {
        return path;
    }

}
