package org.karaffe.compiler.base.types.state;

import org.karaffe.compiler.base.types.InferState;
import org.karaffe.compiler.base.types.InferStateType;

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
