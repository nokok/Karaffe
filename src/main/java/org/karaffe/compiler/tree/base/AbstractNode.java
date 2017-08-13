package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.util.Position;

public abstract class AbstractNode implements Node {

    private final NodeType nodeType;
    private final Position position;

    public AbstractNode(final NodeType nodeType) {
        this(nodeType, Position.noPos());
    }

    public AbstractNode(final NodeType nodeType, final Position position) {
        this.nodeType = nodeType;
        this.position = position;
    }

    @Override
    public String getID() {
        return "";
    }

    @Override
    public NodeType getNodeType() {
        return this.nodeType;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }
}
