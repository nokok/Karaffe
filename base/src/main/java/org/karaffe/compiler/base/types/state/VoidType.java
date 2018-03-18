package org.karaffe.compiler.base.types.state;

import org.karaffe.compiler.base.types.InferState;
import org.karaffe.compiler.base.types.InferStateType;

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
