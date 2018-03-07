package org.karaffe.compiler.types.v2.states;

public class VoidType implements InferState {

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.VOID;
    }

    @Override
    public String toString() {
        return "void";
    }

}
