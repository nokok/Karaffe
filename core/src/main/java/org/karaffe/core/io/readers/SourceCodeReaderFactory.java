package org.karaffe.core.io.readers;

import java.nio.file.Path;

public final class SourceCodeReaderFactory {
    public static RawSourceCodeReadable<Path> fromFileSystemRaw() {
        return new LocalFileSystemSourceCodeReader();
    }

    public static RawSourceCodeReadable<String> directRaw() {
        return new DirectSourceReader();
    }

    public static SourceCodeReadable<Path> fromFileSystem() {
        return new LocalFileSystemSourceCodeReader();
    }

}
