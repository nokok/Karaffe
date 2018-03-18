package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
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

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

    public Node toApplyNode() {
        return new Apply(new Select(this.toLeftApplyNode(), this.getOperator()), this.toRightApplyNode());
    }

    public Node toLeftApplyNode() {
        if (this.getLeft() instanceof BinaryExpr) {
            return ((BinaryExpr) this.getLeft()).toApplyNode();
        }
        return this.getLeft();
    }

    public Node toRightApplyNode() {
        if (this.getRight() instanceof BinaryExpr) {
            return ((BinaryExpr) this.getRight()).toApplyNode();
        }
        return this.getRight();
    }

    @Override
    public String vSource() {
        return String.format("%s", this.toApplyNode().vSource());
    }
}
