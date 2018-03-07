package org.karaffe.compiler.types.v2.states;

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
