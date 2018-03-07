package org.karaffe.compiler.tree.v2.api;

import java.util.Objects;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.types.v2.states.InferState;

public abstract class AbstractTree implements Tree, TreePosition, Typed, InferStateStore {
    private final Position treePosition;
    private InferState inferState;

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
    public boolean hasInferState() {
        return this.inferState != null;
    }

    @Override
    public InferState getInferState() {
        if (!this.hasInferState()) {
            throw new IllegalStateException();
        }
        return this.inferState;
    }

    @Override
    public void setInferState(InferState inferState) {
        this.inferState = Objects.requireNonNull(inferState);
    }

}
