package org.karaffe.compiler.frontend.karaffe.transformer.util;

import java.util.Objects;

public class PhaseResult<R> {
    private enum Result {
        SUCCESS,
        WITH_WARN,
        FAIL
    }

    private final R value;
    private final PhaseResult.Result result;

    private PhaseResult(Result result) {
        this(null, result);
    }

    private PhaseResult(R value, PhaseResult.Result result) {
        this.value = value;
        this.result = result;
    }

    public static PhaseResult<Void> onSuccess() {
        return new PhaseResult<>(Result.SUCCESS);
    }

    public static <R> PhaseResult<R> onSuccess(R value) {
        return new PhaseResult<>(Objects.requireNonNull(value), Result.SUCCESS);
    }

    public static <R> PhaseResult<R> onSuccessNullable(R value) {
        return new PhaseResult<>(value, Result.SUCCESS);
    }

    public static <R> PhaseResult<R> onSuccessWithWarning(R value) {
        return new PhaseResult<>(value, Result.WITH_WARN);
    }

}
