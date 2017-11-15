package org.karaffe.compiler.phases;

import java.util.Optional;
import java.util.function.Function;

public final class PhaseRunner {
    public static <I, O> Function<I, Optional<O>> first(final Transformer<I, O> transformer) {
        return i -> Optional.ofNullable(i)
                .filter(transformer::checkPreCondition)
                .flatMap(transformer::transform)
                .filter(transformer::checkPostCondition);

    }

    public static <I, O> Function<Optional<I>, Optional<O>> after(final Transformer<I, O> transformer) {
        return i -> i.filter(transformer::checkPreCondition)
                .flatMap(transformer::transform)
                .filter(transformer::checkPostCondition);
    }

}
