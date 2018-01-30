package org.karaffe.compiler.tree.visitor;

import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.BinaryExpr;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.Constant;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.Goto;
import org.karaffe.compiler.tree.If;
import org.karaffe.compiler.tree.LabelDef;
import org.karaffe.compiler.tree.LetDef;
import org.karaffe.compiler.tree.Literal.FalseLiteral;
import org.karaffe.compiler.tree.Literal.IntLiteral;
import org.karaffe.compiler.tree.Literal.ThisLiteral;
import org.karaffe.compiler.tree.Literal.TrueLiteral;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Modifier;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.New;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.Operator.Plus;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.Parameters;
import org.karaffe.compiler.tree.Return;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.TypeDefs;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.VarDef;

public abstract class KaraffeTreeVisitorAdapter implements KaraffeTreeVisitor {
    @Override
    public void visit(final Apply node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Assign node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final BinaryExpr node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Block node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final ClassDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final CompileUnit node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Constant node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Empty node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final FalseLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Goto node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final If node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final IntLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final LabelDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final LetDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final MethodDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Modifier node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Modifiers node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Name node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final New node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final NodeList node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final PackageDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Parameters node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Plus node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Return node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final Select node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final ThisLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final TrueLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final TypeDefs node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final TypeName node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(final VarDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }
}
