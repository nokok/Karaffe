package org.karaffe.compiler.types.v2.states;

public class Error implements InferState {

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.ERROR;
    }

    @Override
    public String toString() {
        return "Error";
    }

}
