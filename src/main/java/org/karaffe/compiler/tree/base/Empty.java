package org.karaffe.compiler.tree.base;

import java.util.Optional;

import org.karaffe.compiler.util.Position;

public class Empty extends AbstractNode {

    public Empty() {
        this(Position.noPos());
    }

    public Empty(final Position position) {
        super(NodeType.EMPTY, position);
    }

    @Override
    public Optional<Node> getChildNode() {
        return Optional.empty();
    }

    @Override
    public Optional<Node> getNextNode() {
        return Optional.empty();
    }

}
