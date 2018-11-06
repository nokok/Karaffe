package org.karaffe.compiler.base.attr;

import java.nio.file.Path;
import java.util.Objects;

public class FilePathAttribute extends Attribute {
    private Path filePath;

    public FilePathAttribute(Path filePath) {
        this.filePath = Objects.requireNonNull(filePath);
    }

    public Path getFilePath() {
        return filePath;
    }
}
