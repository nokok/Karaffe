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
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.VarDef;

public class DefaultOperationKaraffeTreeVisitorAdapter implements KaraffeTreeVisitor {
    @Override
    public void visit(Apply node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Assign node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(BinaryExpr node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Block node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(CompileUnit node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(ClassDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Constant node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Empty node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Goto node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(If node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(LabelDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(IntLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(TrueLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(FalseLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(ThisLiteral node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(MethodDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Modifier node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Modifiers node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Name node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(New node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(NodeList node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Plus node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(PackageDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Parameters node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Return node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(Select node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(TypeName node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(TypeDefs node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(ValDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(VarDef node) {
        node.getChildren().forEach(child -> child.accept(this));
    }
}
