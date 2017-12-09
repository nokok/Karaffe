package org.karaffe.compiler.phases;

import java.util.Optional;
import java.util.function.Function;

public interface Transformer<I, O> extends Function<I, Optional<O>> {

    public Class<I> getInputType();

    public Class<O> getOutputType();

    public default boolean checkPreCondition(final I input) {
        return true;
    }

    public default Optional<O> transform(final Optional<I> input) {
        return input.flatMap(this::transform);
    }

    public Optional<O> transform(I input);

    public default boolean checkPostCondition(final O output) {
        return this.checkPostCondition(Optional.ofNullable(output));
    }

    public default boolean checkPostCondition(final Optional<O> output) {
        return true;
    }

    @Override
    default Optional<O> apply(final I input) {
        return this.transform(input);
    }

}
