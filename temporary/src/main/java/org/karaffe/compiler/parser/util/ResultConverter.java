package org.karaffe.compiler.parser.util;

import java.util.Optional;

public interface ResultConverter<S, F> {

    public Optional<F> toFailure();

    public Optional<S> toSuccess();
}
