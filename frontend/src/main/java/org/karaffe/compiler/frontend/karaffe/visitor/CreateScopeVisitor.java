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
    public Tree visit(LetDef tree, Scope scope) {
        super.visit(tree, scope);
        return tree;
    }

    @Override
    public Tree visit(AssignmentDef tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(ClassDef tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(SimpleImport tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(OnDemandImport tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(MethodDef tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(PackageDef tree, Scope scope) {
        return super.visit(tree, scope);
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
    public Tree visit(Tree.CompilationUnit tree, Scope scope) {
        return super.visit(tree, scope.newScope());
    }

    @Override
    public Tree visit(Tree.Template tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(Apply tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(Atom tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(Block tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Path visitOperator(Operator tree, Scope scope) {
        return super.visitOperator(tree, scope);
    }

    @Override
    public Tree visit(Tuple tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(IfExpr tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(WhileExpr tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(EmptyTree tree, Scope scope) {
        return super.visit(tree, scope);
    }

    @Override
    public Tree visit(Cast tree, Scope scope) {
        return super.visit(tree, scope);
    }
}
