package org.karaffe.compiler.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.TypeInferenceContext;

public class Applying implements InferResult {

    private final InferResult applicable;
    private final List<InferResult> args;

    public Applying(InferResult applicable, List<InferResult> args) {
        this.applicable = applicable;
        this.args = new ArrayList<>(args);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isFailed() {
        return false;
    }

    @Override
    public ResultType getInferResultType() {
        return ResultType.APPLYING;
    }

    @Override
    public Optional<InferResult> compose(InferResult other, TypeInferenceContext context) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", this.applicable, String.join(" ,", this.args.stream().map(InferResult::toString).collect(Collectors.toList())));
    }

}
