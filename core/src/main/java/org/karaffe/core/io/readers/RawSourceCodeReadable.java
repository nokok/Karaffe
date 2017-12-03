package org.karaffe.core.io.readers;

import java.util.Optional;

public interface RawSourceCodeReadable<T> {
    public Optional<String> read(T src);
}
