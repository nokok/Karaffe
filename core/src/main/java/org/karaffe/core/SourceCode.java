package org.karaffe.core;

import java.nio.CharBuffer;
import java.nio.file.Path;

public class SourceCode {
    private final String name;
    private final String path;
    private final String rawSourceCode;

    public SourceCode(Path path) {
        this(path, "");
    }

    public SourceCode(Path path, String sourceCode) {
        this.name = path.getFileName().toString();
        this.path = path.toAbsolutePath().toString();
        this.rawSourceCode = sourceCode;
        if (this.rawSourceCode.contains("\r")) {
            throw new IllegalArgumentException("sourceCode contains ('\r')");
        }
    }

    public int offsetToLine(int offset) {
        String range = this.rawSourceCode.substring(0, offset);
        return (int) CharBuffer.wrap(range.toCharArray()).chars().filter(ch -> ch == '\n').count() + 1;
    }

    public String name() {
        return this.name;
    }

    public String toPath() {
        return this.path;
    }

    public int length() {
        return this.rawSourceCode.length();
    }

    public String body() {
        return this.rawSourceCode;
    }

    @Override
    public String toString() {
        return this.rawSourceCode;
    }
}
