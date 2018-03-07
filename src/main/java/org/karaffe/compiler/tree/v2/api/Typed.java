package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.types.v2.states.InferState;

public interface Typed {
    public InferState getInferState();

    public boolean hasInferState();
}
