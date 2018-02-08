package org.karaffe.compiler.types;

import java.util.Optional;

import org.karaffe.compiler.context.TypeContext;

public class NoHint extends Undecidable {
    @Override
    public String toString() {
        return "?";
    }

    @Override
    public ResultType getInferResultType() {
        return ResultType.NO_HINT;
    }

    @Override
    public Optional<InferResult> compose(InferResult other, TypeContext context) {
        return Optional.empty();
    }
}
