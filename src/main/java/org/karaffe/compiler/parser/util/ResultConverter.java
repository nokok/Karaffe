package org.karaffe.compiler.parser.util;

import java.util.Optional;

public interface ResultConverter<S, F> {

    public Optional<S> toSuccess();

    public Optional<F> toFailure();
}
