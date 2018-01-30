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

public class KaraffeTreePrinter implements KaraffeTreeVisitor {

    @Override
    public void visit(final Apply node) {
        System.out.println(node);
    }

    @Override
    public void visit(final Assign node) {
        System.out.println(node);

    }

    @Override
    public void visit(final BinaryExpr node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Block node) {
        System.out.println(node);

    }

    @Override
    public void visit(final ClassDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(final CompileUnit node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Constant node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Empty node) {
        System.out.println(node);

    }

    @Override
    public void visit(final FalseLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Goto node) {
        System.out.println(node);

    }

    @Override
    public void visit(final If node) {
        System.out.println(node);

    }

    @Override
    public void visit(final IntLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(final LabelDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(final LetDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(final MethodDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Modifier node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Modifiers node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Name node) {
        System.out.println(node);

    }

    @Override
    public void visit(final New node) {
        System.out.println(node);

    }

    @Override
    public void visit(final NodeList node) {
        System.out.println(node);

    }

    @Override
    public void visit(final PackageDef node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Parameters node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Plus node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Return node) {
        System.out.println(node);

    }

    @Override
    public void visit(final Select node) {
        System.out.println(node);

    }

    @Override
    public void visit(final ThisLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(final TrueLiteral node) {
        System.out.println(node);

    }

    @Override
    public void visit(final TypeDefs node) {
        System.out.println(node);

    }

    @Override
    public void visit(final TypeName node) {
        System.out.println(node);

    }

    @Override
    public void visit(final VarDef node) {
        System.out.println(node);

    }

}
