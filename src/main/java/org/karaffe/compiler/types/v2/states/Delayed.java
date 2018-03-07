package org.karaffe.compiler.types.v2.states;

public class Delayed implements InferState {

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.DELAYED;
    }

    @Override
    public String toString() {
        return "Delayed";
    }

}
