package org.karaffe.compiler.base.types.state;

public interface InferState {
    static InferState noHint() {
        return new NoHint();
    }

    static InferState voidType() {
        return new VoidType();
    }

    static InferState fail() {
        return new Error();
    }

    public InferStateType getInferStateType();
}
