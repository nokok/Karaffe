package org.karaffe.compiler.phases;

import java.util.Optional;

import com.google.common.base.Function;

public interface Transformer<I, O> extends Function<I, Optional<O>> {
    public default boolean checkPreCondition(final I input) {
        return true;
    }

    public Optional<O> transform(I input);

    public default boolean checkPostCondition(final O output) {
        return true;
    }

    @Override
    default Optional<O> apply(final I input) {
        return this.transform(input);
    }

}
