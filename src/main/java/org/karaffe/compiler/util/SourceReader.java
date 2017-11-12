package org.karaffe.compiler.util;

import java.io.File;
import java.util.Optional;

public interface SourceReader {
    public Optional<File> read();
}
