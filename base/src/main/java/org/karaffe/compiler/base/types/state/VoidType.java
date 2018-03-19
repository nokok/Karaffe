package org.karaffe.compiler.base.types.state;

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
