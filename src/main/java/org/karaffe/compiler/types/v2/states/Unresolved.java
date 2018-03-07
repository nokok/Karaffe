package org.karaffe.compiler.types.v2.states;

public class Unresolved implements InferState {

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.UNRESOLVED;
    }

    @Override
    public String toString() {
        return "Unresolved";
    }

}
