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
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Modifier;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.New;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.Operator;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.Parameters;
import org.karaffe.compiler.tree.Return;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.TypeDefs;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.VarDef;

public interface KaraffeTreeVisitor {
    public void visit(Apply node);

    public void visit(Assign node);

    public void visit(BinaryExpr node);

    public void visit(Block node);

    public void visit(ClassDef node);

    public void visit(CompileUnit node);

    public void visit(Constant node);

    public void visit(Empty node);

    public void visit(Goto node);

    public void visit(If node);

    public void visit(LabelDef node);

    public void visit(LetDef node);

    public void visit(Literal.FalseLiteral node);

    public void visit(Literal.IntLiteral node);

    public void visit(Literal.ThisLiteral node);

    public void visit(Literal.TrueLiteral node);

    public void visit(MethodDef node);

    public void visit(Modifier node);

    public void visit(Modifiers node);

    public void visit(Name node);

    public void visit(New node);

    public void visit(NodeList node);

    public void visit(Operator.Plus node);

    public void visit(PackageDef node);

    public void visit(Parameters node);

    public void visit(Return node);

    public void visit(Select node);

    public void visit(TypeDefs node);

    public void visit(TypeName node);

    public void visit(VarDef node);

}
