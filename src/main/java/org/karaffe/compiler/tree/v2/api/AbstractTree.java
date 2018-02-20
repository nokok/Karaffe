package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.pos.Position;

public abstract class AbstractTree implements Tree {
    private final Position treePosition;

    public AbstractTree() {
        this(Position.noPos());
    }

    public AbstractTree(Position position) {
        this.treePosition = position;
    }

    public Position getPosition() {
        return this.treePosition;
    }
}
