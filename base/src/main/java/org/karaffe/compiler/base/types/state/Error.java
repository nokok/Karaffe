package org.karaffe.compiler.base.types.state;

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
