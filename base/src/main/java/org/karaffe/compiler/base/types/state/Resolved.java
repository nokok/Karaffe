package org.karaffe.compiler.base.types.state;

public class Resolved implements InferState {
    @Override
    public InferStateType getInferStateType() {
        return InferStateType.RESOLVED;
    }
}
