package org.karaffe.compiler.tree.v2.api;

import java.util.Objects;

import org.karaffe.compiler.pos.Position;

public abstract class AbstractTree implements Tree, TreePosition {
    private final Position treePosition;

    public AbstractTree() {
        this(Position.noPos());
    }

    public AbstractTree(Position position) {
        this.treePosition = Objects.requireNonNull(position);
    }

    @Override
    public Position getPosition() {
        return this.treePosition;
    }

}
