package org.karaffe.compiler.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;

public class Successful implements InferResult {

    private final List<String> types;

    public Successful(final String... possibleTypes) {
        Objects.requireNonNull(possibleTypes);
        if (possibleTypes.length == 0) {
            throw new IllegalArgumentException();
        }
        this.types = new ArrayList<>(Arrays.asList(possibleTypes));
    }

    public Successful(List<String> possibleTypes) {
        Objects.requireNonNull(possibleTypes);
        if (possibleTypes.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.types = new ArrayList<>(possibleTypes);
    }

    @Override
    public Optional<String> getType() {
        return Optional.of(this.types.get(0));
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public String toString() {
        return String.join(" | ", this.types);
    }

    @Override
    public boolean isFailed() {
        return false;
    }

    @Override
    public ResultType getInferResultType() {
        return ResultType.SUCCESSFUL;
    }

    @Override
    public Optional<InferResult> compose(InferResult other, TypeInferenceContext context) {
        if (other.isFailed()) {
            return Optional.empty();
        }
        if (other.getInferResultType() == ResultType.ANY_TARGET) {
            // Successful#AnyTarget -> MayBeApplicable
            AnyTarget anyTarget = (AnyTarget) other;
            return Optional.of(InferResult.mayBeApplicable(this, anyTarget.getName()));
        }
        return Optional.empty();
    }

}
