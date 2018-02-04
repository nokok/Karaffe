package org.karaffe.compiler.types;

import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;

public class Failed implements InferResult {

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public boolean isFailed() {
        return true;
    }

    @Override
    public ResultType getInferResultType() {
        return ResultType.FAILED;
    }

    @Override
    public Optional<InferResult> compose(InferResult other, TypeInferenceContext context) {
        return Optional.empty();
    }
}
