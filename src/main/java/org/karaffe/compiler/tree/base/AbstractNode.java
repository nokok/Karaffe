package org.karaffe.compiler.tree.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.NodeType;

public abstract class AbstractNode implements Node {

    private final String id = UUID.randomUUID().toString();
    private final NodeType nodeType;
    private final Position position;
    private final List<Node> children;

    public AbstractNode(final NodeType nodeType) {
        this(nodeType, Position.noPos());
    }

    public <T extends Node> AbstractNode(final NodeType nodeType, final List<T> children) {
        Objects.requireNonNull(nodeType);
        Objects.requireNonNull(children);
        if (children.stream().filter(f -> f == null).count() > 0) {
            throw new NullPointerException("null children found. :" + children);
        }
        this.nodeType = nodeType;
        if (children.isEmpty()) {
            this.position = Position.noPos();
        } else {
            this.position = children
                    .stream()
                    .map(Node::getPosition)
                    .reduce(Position::merge)
                    .orElse(Position.noPos());
        }
        this.children = (List<Node>) children;

    }

    public AbstractNode(final NodeType nodeType, final Node children) {
        this(nodeType, children.getPosition(), new ArrayList<>(Arrays.asList(children)));
    }

    public AbstractNode(final NodeType nodeType, final Node... children) {
        this(nodeType, new ArrayList<>(Arrays.asList(children)));
    }

    public AbstractNode(final NodeType nodeType, final Position position) {
        this(nodeType, position, new ArrayList<>(0));
    }

    public AbstractNode(final NodeType nodeType, final Position position, final List<Node> children) {
        this.nodeType = Objects.requireNonNull(nodeType);
        this.position = Objects.requireNonNull(position);
        this.children = Objects.requireNonNull(children);
        if (children.stream().filter(f -> f == null).count() > 0) {
            throw new NullPointerException("null children found. :" + children);
        }
    }

    public AbstractNode(final NodeType nodeType, final Position position, final Node children) {
        this(nodeType, position, new ArrayList<>(Arrays.asList(children)));
    }

    @Override
    public void addChild(final Node n) {
        this.children.add(Objects.requireNonNull(n));
    }

    @Override
    protected AbstractNode clone() {
        try {
            final AbstractNode cloned = (AbstractNode) super.clone();
            return cloned;
        } catch (final CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<? extends Node> getChildren() {
        return new ArrayList<>(this.children);
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
    public void replaceChildren(final List<Node> replaced) {
        this.children.clear();
        this.children.addAll(replaced);
    }

    @Override
    public String toString() {
        return String.format("(%s %s)", this.getClass().getSimpleName(), this.children.stream().map(Object::toString).reduce((l, r) -> l + " " + r).orElse("()"));
    }

}
