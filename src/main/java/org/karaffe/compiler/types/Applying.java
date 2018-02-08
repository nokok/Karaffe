package org.karaffe.compiler.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.context.TypeContext;

public class Applying implements InferResult {

    private final MayBeApplicable applicable;
    private final List<InferResult> args;

    public Applying(MayBeApplicable applicable, List<InferResult> args) {
        this.applicable = applicable;
        this.args = new ArrayList<>(args);
    }

    public MayBeApplicable getApplicable() {
        return this.applicable;
    }

    public List<InferResult> getArgs() {
        return new ArrayList<>(this.args);
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
    public Optional<InferResult> compose(InferResult other, TypeContext context) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", this.applicable, String.join(" ,", this.args.stream().map(InferResult::toString).collect(Collectors.toList())));
    }

}
