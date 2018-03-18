package org.karaffe.compiler.base.types.state;

import org.karaffe.compiler.base.types.InferState;
import org.karaffe.compiler.base.types.InferStateType;

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
