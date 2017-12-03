package org.karaffe.core.io.readers;

import java.util.Optional;

import org.karaffe.core.SourceCode;

public interface SourceCodeReadable<T> {
    public Optional<SourceCode> readAsSourceCode(T src);
}
