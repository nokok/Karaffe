package org.karaffe.compiler.base.types.state;

public class NoHint implements InferState {

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.NO_HINT;
    }

    @Override
    public String toString() {
        return "NoHint";
    }

}
