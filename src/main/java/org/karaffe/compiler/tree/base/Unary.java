package org.karaffe.compiler.tree.base;

import java.util.Objects;
import java.util.Optional;

import org.karaffe.compiler.util.Position;

public class Unary extends AbstractNode {

    private final Node node;

    public Unary(final NodeType nodeType, final Node node) {
        super(nodeType, Position.noPos());
        this.node = Objects.requireNonNull(node);
    }

    @Override
    public Optional<Node> getChildNode() {
        return Optional.of(this.node);
    }

    @Override
    public Optional<Node> getNextNode() {
        return Optional.empty();
    }

}
