package org.karaffe.core.io;

import java.util.Optional;

public interface RawSourceCodeReadable<T> {
    public Optional<String> read(T src);
}
