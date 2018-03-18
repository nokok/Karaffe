package org.karaffe.compiler.base.types;

import org.karaffe.compiler.base.types.state.Error;
import org.karaffe.compiler.base.types.state.NoHint;
import org.karaffe.compiler.base.types.state.VoidType;

public class InferStates {

    public static InferState noHint() {
        return new NoHint();
    }

    public static InferState voidType() {
        return new VoidType();
    }

    public static InferState fail() {
        return new Error();
    }

}
