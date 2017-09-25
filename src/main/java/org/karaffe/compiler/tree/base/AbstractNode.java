package org.karaffe.compiler.tree.base;

import java.util.UUID;

import org.karaffe.compiler.util.Position;

public abstract class AbstractNode implements Node {

    private final String id = UUID.randomUUID().toString();
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
        return this.id;
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
