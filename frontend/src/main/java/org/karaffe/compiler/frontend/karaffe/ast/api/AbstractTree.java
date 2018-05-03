package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.base.pos.Position;

import java.util.Objects;

public abstract class AbstractTree implements Tree, TreePosition {
    private Position treePosition = Position.noPos();

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

    @Override
    public void resetPosition(Position position) {
        this.treePosition = position;
    }

}
