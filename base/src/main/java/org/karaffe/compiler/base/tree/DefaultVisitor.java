package org.karaffe.compiler.base.tree;

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
        Tree name = tree.getName().accept(this, p);
        Tree typeName = tree.getTypeName().accept(this, p);
        List<Tree> trees = visitChildren(tree, p);
        List<Tree> modifiers = visitModifiers(tree, p);
        tree.setName(name);
        tree.setTypeName(typeName);
        tree.setChildren(trees);
        tree.setModifiers(modifiers);
        return tree;
    }

    @Override
    public Tree visit(LetDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(AssignmentDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(ClassDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(SimpleImport tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(OnDemandImport tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(MethodDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(PackageDef tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Tree.CompilationUnit tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Tree.Template tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Apply tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Block tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Tuple tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(IfExpr tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(WhileExpr tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(EmptyTree tree, P p) {
        return tree;
    }

    @Override
    public Tree visit(Cast tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Binding tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(ReturnStatement tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(PublicModifier tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(StaticModifier tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(SyntheticModifier tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Plus plus, P p) {
        return null;
    }

    @Override
    public Tree visit(Pow pow, P p) {
        return null;
    }

    @Override
    public Tree visit(TypeName typeName, P p) {
        return typeName;
    }

    @Override
    public Tree visit(VarName varName, P p) {
        return varName;
    }

    @Override
    public Tree visit(Array array, P p) {
        return array;
    }

    @Override
    public Tree visit(Void aVoid, P p) {
        return aVoid;
    }

    @Override
    public Tree visit(InternalName internalName, P p) {
        return internalName;
    }

    @Override
    public Tree visit(StringLiteral stringLiteral, P p) {
        return stringLiteral;
    }

    @Override
    public Tree visit(IntegerLiteral integerLiteral, P p) {
        return integerLiteral;
    }

    @Override
    public Tree visit(Identifier identifier, P p) {
        return identifier;
    }

    @Override
    public Tree visit(Range range, P p) {
        return null;
    }

    @Override
    public Tree visit(Comma comma, P p) {
        return null;
    }

    @Override
    public Tree visit(Or or, P p) {
        return null;
    }

    @Override
    public Tree visit(NotEqualsTo notEqualsTo, P p) {
        return null;
    }

    @Override
    public Tree visit(Mul mul, P p) {
        return null;
    }

    @Override
    public Tree visit(Mod mod, P p) {
        return null;
    }

    @Override
    public Tree visit(Minus minus, P p) {
        return null;
    }

    @Override
    public Tree visit(And and, P p) {
        return null;
    }

    @Override
    public Tree visit(Bang bang, P p) {
        return null;
    }

    @Override
    public Tree visit(DeepEqualsTo deepEqualsTo, P p) {
        return null;
    }

    @Override
    public Tree visit(DeepNotEqualsTo deepNotEqualsTo, P p) {
        return null;
    }

    @Override
    public Tree visit(Div div, P p) {
        return null;
    }

    @Override
    public Tree visit(EqualsTo equalsTo, P p) {
        return null;
    }

    @Override
    public Tree visit(GreaterThan greaterThan, P p) {
        return null;
    }

    @Override
    public Tree visit(GreaterThanEquals greaterThanEquals, P p) {
        return null;
    }

    @Override
    public Tree visit(LessThanEquals lessThanEquals, P p) {
        return null;
    }

    @Override
    public Tree visit(LessThan lessThan, P p) {
        return null;
    }

}
