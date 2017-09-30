package org.karaffe.compiler.tree.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.util.Position;

public abstract class AbstractNode implements Node {

    private final String id = UUID.randomUUID().toString();
    private final NodeType nodeType;
    private final Position position;
    private final List<? extends Node> children;

    public AbstractNode(final NodeType nodeType) {
        this(nodeType, Position.noPos());
    }

    public AbstractNode(final NodeType nodeType, final Position position) {
        this(nodeType, position, new ArrayList<>(0));
    }

    public AbstractNode(final NodeType nodeType, final Position position, final Node children) {
        this(nodeType, position, new ArrayList<>(Arrays.asList(children)));
    }

    public AbstractNode(final NodeType nodeType, final Node children) {
        this(nodeType, children.getPosition(), new ArrayList<>(Arrays.asList(children)));
    }

    public AbstractNode(final NodeType nodeType, final List<? extends Node> children) {
        this.nodeType = nodeType;
        if (children.isEmpty()) {
            this.position = Position.noPos();
        } else {
            this.position = Position.ofRange(children.get(0).getPosition(), children.get(children.size() - 1).getPosition());
        }
        this.children = children;
    }

    public AbstractNode(final NodeType nodeType, final Position position, final List<? extends Node> children) {
        this.nodeType = nodeType;
        this.position = position;
        this.children = children;
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

    @Override
    public List<? extends Node> getChildren() {
        return new ArrayList<>(this.children);
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", this.nodeType.name(), this.position, this.children);
    }
}
