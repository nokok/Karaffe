package org.karaffe.compiler.tree.base;

public abstract class OperatorApply extends AbstractNode {

    private final Node left;
    private final Node operator;
    private final Node right;

    public OperatorApply(final NodeType nodeType, final Node left, final Node operator, final Node right) {
        super(nodeType, left.getPosition());
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getOperator() {
        return this.operator;
    }

    public Node getRight() {
        return this.right;
    }
}
