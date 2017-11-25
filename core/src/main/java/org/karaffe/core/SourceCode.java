package org.karaffe.core;

import java.nio.file.Path;

public class SourceCode {
    private final String name;
    private final String path;
    private final String rawSourceCode;

    public SourceCode(Path path) {
        this.name = path.getFileName().toString();
        this.path = path.toAbsolutePath().toString();
        this.rawSourceCode = "";
    }

    public String name() {
        return this.name;
    }

    public String toPath() {
        return this.path;
    }

    public String body() {
        return this.rawSourceCode;
    }

    @Override
    public String toString() {
        return this.rawSourceCode;
    }
}
