package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;

public class BinaryExpr extends AbstractNode {

    public BinaryExpr(final Node left, final Node op, final Node right) {
        super(NodeType.EXPR, left, op, right);
    }

    public Node getLeft() {
        return this.getChildren().get(0);
    }

    public Node getOperator() {
        return this.getChildren().get(1);
    }

    public Node getRight() {
        return this.getChildren().get(2);
    }

    public Node toLeftApplyNode() {
        if (this.getLeft() instanceof BinaryExpr) {
            return new Apply(((BinaryExpr) this.getLeft()).toApplyNode());
        }
        return this.getLeft();
    }

    public Node toRightApplyNode() {
        if (this.getRight() instanceof BinaryExpr) {
            return new Apply(((BinaryExpr) this.getRight()).toApplyNode());
        }
        return this.getRight();
    }

    public Node toApplyNode() {
        return new Apply(new Select(this.toLeftApplyNode(), this.getOperator()), this.toRightApplyNode());
    }

    @Override
    public AbstractNodes normalize() {
        return super.normalize();
    }

}
