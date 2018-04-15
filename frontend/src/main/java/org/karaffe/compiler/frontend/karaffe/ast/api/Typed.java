package org.karaffe.compiler.frontend.karaffe.ast.api;


import org.karaffe.compiler.base.types.state.InferState;

public interface Typed {
    public InferState getInferState();

    public boolean hasInferState();
}
