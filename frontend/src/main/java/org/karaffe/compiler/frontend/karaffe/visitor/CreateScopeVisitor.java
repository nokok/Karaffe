package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
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
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.util.Scope;

public class CreateScopeVisitor extends DefaultVisitor<Scope> {

    private Scope currentScope;

    public Scope getCurrentScope() {
        return currentScope;
    }

    @Override
    public Tree visitLetDef(LetDef tree, Scope scope) {
        super.visitLetDef(tree, scope);
        return tree;
    }

    @Override
    public Tree visitAssignmentDef(AssignmentDef tree, Scope scope) {
        return super.visitAssignmentDef(tree, scope);
    }

    @Override
    public Tree visitClassDef(ClassDef tree, Scope scope) {
        return super.visitClassDef(tree, scope);
    }

    @Override
    public Tree visitSimpleImportDef(SimpleImport tree, Scope scope) {
        return super.visitSimpleImportDef(tree, scope);
    }

    @Override
    public Tree visitOnDemandImportDef(OnDemandImport tree, Scope scope) {
        return super.visitOnDemandImportDef(tree, scope);
    }

    @Override
    public Tree visitMethodDef(MethodDef tree, Scope scope) {
        return super.visitMethodDef(tree, scope);
    }

    @Override
    public Tree visitPackageDef(PackageDef tree, Scope scope) {
        return super.visitPackageDef(tree, scope);
    }

    @Override
    public Tree visitStaticMod(Modifier tree, Scope scope) {
        return super.visitStaticMod(tree, scope);
    }

    @Override
    public Tree visitPublicMod(Modifier tree, Scope scope) {
        return super.visitPublicMod(tree, scope);
    }

    @Override
    public Tree visitCompileUnit(Tree.CompilationUnit tree, Scope scope) {
        return super.visitCompileUnit(tree, scope.newScope());
    }

    @Override
    public Tree visitTemplate(Tree.Template tree, Scope scope) {
        return super.visitTemplate(tree, scope);
    }

    @Override
    public Tree visitApply(Apply tree, Scope scope) {
        return super.visitApply(tree, scope);
    }

    @Override
    public Tree visitAtom(Atom tree, Scope scope) {
        return super.visitAtom(tree, scope);
    }

    @Override
    public Tree visitBlock(Block tree, Scope scope) {
        return super.visitBlock(tree, scope);
    }

    @Override
    public Tree visitSelect(Select tree, Scope scope) {
        return super.visitSelect(tree, scope);
    }

    @Override
    public Path visitOperator(Operator tree, Scope scope) {
        return super.visitOperator(tree, scope);
    }

    @Override
    public Tree visitTuple(Tuple tree, Scope scope) {
        return super.visitTuple(tree, scope);
    }

    @Override
    public Tree visitIfExpr(IfExpr tree, Scope scope) {
        return super.visitIfExpr(tree, scope);
    }

    @Override
    public Tree visitWhileExpr(WhileExpr tree, Scope scope) {
        return super.visitWhileExpr(tree, scope);
    }

    @Override
    public Tree visitEmpty(EmptyTree tree, Scope scope) {
        return super.visitEmpty(tree, scope);
    }

    @Override
    public Tree visitCast(Cast tree, Scope scope) {
        return super.visitCast(tree, scope);
    }
}
