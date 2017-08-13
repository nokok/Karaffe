package org.karaffe.compiler.tree.base;

import java.util.Objects;
import java.util.Optional;

public class Binary extends AbstractNode {

    private final Node child;
    private final Node next;

    public Binary(final NodeType nodeType, final Node child, final Node next) {
        super(nodeType);
        this.child = Objects.requireNonNull(child);
        this.next = Objects.requireNonNull(next);
    }

    @Override
    public Optional<Node> getChildNode() {
        return Optional.of(this.child);
    }

    @Override
    public Optional<Node> getNextNode() {
        return Optional.of(this.next);
    }

}
