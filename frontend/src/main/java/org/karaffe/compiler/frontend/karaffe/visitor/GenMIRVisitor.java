package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.base.attr.NameAttribute;
import org.karaffe.compiler.base.ir.Class;
import org.karaffe.compiler.base.ir.Element;
import org.karaffe.compiler.base.ir.IR;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.def.AssignmentDef;
import org.karaffe.compiler.base.tree.def.ClassDef;
import org.karaffe.compiler.base.tree.def.LetDef;
import org.karaffe.compiler.base.tree.def.MethodDef;
import org.karaffe.compiler.base.tree.def.OnDemandImport;
import org.karaffe.compiler.base.tree.def.PackageDef;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.expr.Apply;
import org.karaffe.compiler.base.tree.expr.Binding;
import org.karaffe.compiler.base.tree.expr.Block;
import org.karaffe.compiler.base.tree.expr.Cast;
import org.karaffe.compiler.base.tree.expr.Identifier;
import org.karaffe.compiler.base.tree.expr.IfExpr;
import org.karaffe.compiler.base.tree.expr.IntegerLiteral;
import org.karaffe.compiler.base.tree.expr.StringLiteral;
import org.karaffe.compiler.base.tree.expr.Tuple;
import org.karaffe.compiler.base.tree.expr.WhileExpr;
import org.karaffe.compiler.base.tree.expr.op.And;
import org.karaffe.compiler.base.tree.expr.op.Bang;
import org.karaffe.compiler.base.tree.expr.op.Comma;
import org.karaffe.compiler.base.tree.expr.op.DeepEqualsTo;
import org.karaffe.compiler.base.tree.expr.op.DeepNotEqualsTo;
import org.karaffe.compiler.base.tree.expr.op.Div;
import org.karaffe.compiler.base.tree.expr.op.EqualsTo;
import org.karaffe.compiler.base.tree.expr.op.GreaterThan;
import org.karaffe.compiler.base.tree.expr.op.GreaterThanEquals;
import org.karaffe.compiler.base.tree.expr.op.LessThan;
import org.karaffe.compiler.base.tree.expr.op.LessThanEquals;
import org.karaffe.compiler.base.tree.expr.op.Minus;
import org.karaffe.compiler.base.tree.expr.op.Mod;
import org.karaffe.compiler.base.tree.expr.op.Mul;
import org.karaffe.compiler.base.tree.expr.op.NotEqualsTo;
import org.karaffe.compiler.base.tree.expr.op.Or;
import org.karaffe.compiler.base.tree.expr.op.Plus;
import org.karaffe.compiler.base.tree.expr.op.Pow;
import org.karaffe.compiler.base.tree.expr.op.Range;
import org.karaffe.compiler.base.tree.modifier.PublicModifier;
import org.karaffe.compiler.base.tree.modifier.StaticModifier;
import org.karaffe.compiler.base.tree.modifier.SyntheticModifier;
import org.karaffe.compiler.base.tree.stmt.ReturnStatement;
import org.karaffe.compiler.base.tree.term.EmptyTree;
import org.karaffe.compiler.base.tree.term.InternalName;
import org.karaffe.compiler.base.tree.term.TypeName;
import org.karaffe.compiler.base.tree.term.VarName;
import org.karaffe.compiler.base.tree.type.Array;
import org.karaffe.compiler.base.tree.type.primitive.Void;

public class GenMIRVisitor implements TreeVisitor<Element, Element> {

    @Override
    public Element visit(LetDef simpleDef, Element parent) {
        return null;
    }

    @Override
    public Element visit(AssignmentDef simpleDef, Element parent) {
        return null;
    }

    @Override
    public Element visit(ClassDef def, Element parent) {
        Class clazz = new Class();
        clazz.addAttribute(new NameAttribute(def.getName().toString()));
        parent.add(clazz);
        return clazz;
    }

    @Override
    public Element visit(SimpleImport def, Element parent) {
        return null;
    }

    @Override
    public Element visit(OnDemandImport onDemandImport, Element parent) {
        return null;
    }

    @Override
    public Element visit(MethodDef def, Element parent) {
        return null;
    }

    @Override
    public Element visit(PackageDef def, Element parent) {
        return null;
    }

    @Override
    public Element visit(Tree.CompilationUnit tree, Element parent) {
        IR ir = IR.newIR();
        tree.acceptChildren(this, (Element) ir);
        return (Element) ir;
    }

    @Override
    public Element visit(Tree.Template template, Element parent) {
        return null;
    }

    @Override
    public Element visit(Apply apply, Element parent) {
        return null;
    }

    @Override
    public Element visit(Block block, Element parent) {
        return null;
    }

    @Override
    public Element visit(Tuple tuple, Element parent) {
        return null;
    }

    @Override
    public Element visit(IfExpr ifExpr, Element parent) {
        return null;
    }

    @Override
    public Element visit(WhileExpr whileExpr, Element parent) {
        return null;
    }

    @Override
    public Element visit(EmptyTree emptyTree, Element parent) {
        return null;
    }

    @Override
    public Element visit(Cast cast, Element parent) {
        return null;
    }

    @Override
    public Element visit(Binding binding, Element parent) {
        return null;
    }

    @Override
    public Element visit(ReturnStatement returnStatement, Element parent) {
        return null;
    }

    @Override
    public Element visit(PublicModifier publicModifier, Element parent) {
        return null;
    }

    @Override
    public Element visit(StaticModifier staticModifier, Element parent) {
        return null;
    }

    @Override
    public Element visit(SyntheticModifier syntheticModifier, Element parent) {
        return null;
    }

    @Override
    public Element visit(StringLiteral stringLiteral, Element parent) {
        return null;
    }

    @Override
    public Element visit(IntegerLiteral integerLiteral, Element parent) {
        return null;
    }

    @Override
    public Element visit(Identifier identifier, Element parent) {
        return null;
    }

    @Override
    public Element visit(Range range, Element parent) {
        return null;
    }

    @Override
    public Element visit(Comma comma, Element parent) {
        return null;
    }

    @Override
    public Element visit(Or or, Element parent) {
        return null;
    }

    @Override
    public Element visit(NotEqualsTo notEqualsTo, Element parent) {
        return null;
    }

    @Override
    public Element visit(Mul mul, Element parent) {
        return null;
    }

    @Override
    public Element visit(Mod mod, Element parent) {
        return null;
    }

    @Override
    public Element visit(Minus minus, Element parent) {
        return null;
    }

    @Override
    public Element visit(And and, Element parent) {
        return null;
    }

    @Override
    public Element visit(Bang bang, Element parent) {
        return null;
    }

    @Override
    public Element visit(DeepEqualsTo deepEqualsTo, Element parent) {
        return null;
    }

    @Override
    public Element visit(DeepNotEqualsTo deepNotEqualsTo, Element parent) {
        return null;
    }

    @Override
    public Element visit(Div div, Element parent) {
        return null;
    }

    @Override
    public Element visit(EqualsTo equalsTo, Element parent) {
        return null;
    }

    @Override
    public Element visit(GreaterThan greaterThan, Element parent) {
        return null;
    }

    @Override
    public Element visit(GreaterThanEquals greaterThanEquals, Element parent) {
        return null;
    }

    @Override
    public Element visit(LessThanEquals lessThanEquals, Element parent) {
        return null;
    }

    @Override
    public Element visit(LessThan lessThan, Element parent) {
        return null;
    }

    @Override
    public Element visit(Plus plus, Element parent) {
        return null;
    }

    @Override
    public Element visit(Pow pow, Element parent) {
        return null;
    }

    @Override
    public Element visit(TypeName typeName, Element parent) {
        return null;
    }

    @Override
    public Element visit(VarName varName, Element parent) {
        return null;
    }

    @Override
    public Element visit(Array array, Element parent) {
        return null;
    }

    @Override
    public Element visit(Void aVoid, Element parent) {
        return null;
    }

    @Override
    public Element visit(InternalName internalName, Element parent) {
        return null;
    }
}
