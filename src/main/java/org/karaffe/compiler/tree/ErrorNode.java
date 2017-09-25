package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.base.NodeType;
import org.karaffe.compiler.util.Position;

public class ErrorNode extends AbstractNode {

    private final Node node;

    public ErrorNode(final Position position) {
        this(null, position);
    }

    public ErrorNode(final Node node, final Position position) {
        super(NodeType.ERROR, position);
        this.node = node;
    }

    public Node getErrorNode() {
        return this.node;
    }

    @Override
    public String toString() {
        return String.format("(ERROR %s)", this.node);
    }

}
