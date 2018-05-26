package org.karaffe.compiler.base.tree;

public class BinaryExpr extends AbstractTree {

    public BinaryExpr(Tree parent) {
        super(parent, TreeKind.BINARY_EXPR);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

}
