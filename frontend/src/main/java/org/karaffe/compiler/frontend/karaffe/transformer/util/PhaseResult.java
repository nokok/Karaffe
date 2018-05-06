package org.karaffe.compiler.frontend.karaffe.transformer.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public R getOrThrow() {
        return getOrThrow(NullPointerException::new);
    }

    public R getOrThrow(Supplier<RuntimeException> f) {
        if (this.value == null) {
            throw f.get();
        }
        return this.value;
    }

    public <O> O fold(Supplier<O> fail, Function<R, O> success) {
        if (this.value == null) {
            return fail.get();
        } else {
            return success.apply(this.value);
        }
    }

    public <O> Optional<O> map(Function<R, O> f) {
        if (this.value == null) {
            return Optional.empty();
        } else {
            try {
                return Optional.ofNullable(f.apply(this.value));
            } catch (RuntimeException e) {
                return Optional.empty();
            }
        }
    }

    public void ifPresent(Consumer<R> valueConsumer) {
        if (this.value != null) {
            valueConsumer.accept(this.value);
        }
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
