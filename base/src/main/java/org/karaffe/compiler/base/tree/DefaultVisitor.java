package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.def.Def;
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
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.type.ArrayType;
import org.karaffe.compiler.base.tree.type.PrimitiveType;
import org.karaffe.compiler.base.tree.type.SimpleType;
import org.karaffe.compiler.base.tree.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultVisitor<P> implements TreeVisitor<Tree, P> {

    private List<Tree> visitChildren(Tree t, P p) {
        if (t.getKind() != TreeKind.NAME) {
            return t.getChildren().stream().map(c -> c.accept(this, p)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private List<Tree> visitModifiers(Tree t, P p) {
        return t.getModifiers().stream().map(m -> m.accept(this, p)).collect(Collectors.toList());
    }

    private Tree visitTree(Tree tree, P p) {
        if (tree.asType() != null) {
            tree.setType(tree.asType().accept(this, p));
        }
        tree.setChildren(visitChildren(tree, p));
        tree.setModifiers(visitModifiers(tree, p));
        if (tree.getName() != null) {
            tree.setName(tree.getName().accept(this, p));
        }
        return tree;
    }

    @Override
    public Tree visitLetDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitAssignmentDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitClassDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitSimpleImportDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitOnDemandImportDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitMethodDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPackageDef(Def tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitNoneName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitFQCN(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPackageName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitThisName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitVarName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitTypeName(Name tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visit(Modifier tree, P p) {
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
    public Tree visitRefType(SimpleType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitArrayType(ArrayType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveIntType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveCharType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveByteType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitVoidType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveBooleanType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveLongType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveFloatType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveShortType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitPrimitiveDoubleType(PrimitiveType tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitNoType(Type tree, P p) {
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
    public Tree visitSelect(Select tree, P p) {
        return visitTree(tree, p);
    }

    @Override
    public Tree visitOperator(Operator tree, P p) {
        return visitTree(tree, p);
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
}
