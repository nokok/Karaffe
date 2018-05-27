package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class BinaryExpr extends AbstractExpr {

    public BinaryExpr() {
        this(null);
    }

    public BinaryExpr(Tree parent) {
        super(parent, ExprKind.BINARY);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    public void setLeftExpr(Tree tree) {
        this.setOrReplaceChild(0, tree);
    }

    public void setOp(Tree tree) {
        this.setOrReplaceChild(1, tree);
    }

    public void setRightExpr(Tree tree) {
        this.setOrReplaceChild(2, tree);
    }

}
