package org.karaffe.compiler.frontend.karaffe.ast.api;

import java.util.Objects;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.types.state.InferState;

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
