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

public class KaraffeTreePrinter implements KaraffeTreeVisitor {

    @Override
    public void visit(Apply node) {
        System.out.println(node);
    }

    @Override
    public void visit(Assign node) {
        System.out.println(node);

    }

    @Override
    public void visit(BinaryExpr node) {
        System.out.println(node);

    }

    @Override
    public void visit(Block node) {
        System.out.println(node);

    }

    @Override
    public void visit(CompileUnit node) {
        System.out.println(node);

    }

    @Override
    public void visit(ClassDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(Constant node) {
        System.out.println(node);

    }

    @Override
    public void visit(Empty node) {
        System.out.println(node);

    }

    @Override
    public void visit(Goto node) {
        System.out.println(node);

    }

    @Override
    public void visit(If node) {
        System.out.println(node);

    }

    @Override
    public void visit(LabelDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(IntLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(TrueLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(FalseLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(ThisLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(MethodDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(Modifier node) {
        System.out.println(node);

    }

    @Override
    public void visit(Modifiers node) {
        System.out.println(node);

    }

    @Override
    public void visit(Name node) {
        System.out.println(node);

    }

    @Override
    public void visit(New node) {
        System.out.println(node);

    }

    @Override
    public void visit(NodeList node) {
        System.out.println(node);

    }

    @Override
    public void visit(Plus node) {
        System.out.println(node);

    }

    @Override
    public void visit(PackageDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(Parameters node) {
        System.out.println(node);

    }

    @Override
    public void visit(Return node) {
        System.out.println(node);

    }

    @Override
    public void visit(Select node) {
        System.out.println(node);

    }

    @Override
    public void visit(TypeName node) {
        System.out.println(node);

    }

    @Override
    public void visit(TypeDefs node) {
        System.out.println(node);

    }

    @Override
    public void visit(ValDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(VarDef node) {
        System.out.println(node);

    }

}
