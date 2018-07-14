package org.karaffe.compiler.base.util;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SourceFile {

    public static final String UNKNOWN_FILE_NAME = "<empty>";

    private final String fileName;
    private final String absolutePath;
    private final List<String> lines;

    public SourceFile(String fileName, String absolutePath, List<String> lines) {
        this.fileName = Objects.requireNonNull(fileName);
        this.absolutePath = Objects.requireNonNull(absolutePath);
        this.lines = Objects.requireNonNull(lines);
    }

    public SourceFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Unexpected directory");
        }
        this.fileName = file.getName();
        this.absolutePath = file.getAbsolutePath();
        try {
            this.lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean isUnknownFile() {
        return this.fileName.equals(UNKNOWN_FILE_NAME);
    }

    public Path toPath() {
        if (this.isUnknownFile()) {
            throw new UnsupportedOperationException();
        }
        return Paths.get(this.absolutePath);
    }

    @Override
    public String toString() {
        return String.join("\n", this.lines) + "\n";
    }

    public static SourceFile fromLiteral(String source) {
        String[] lines = source.split("\n");
        return new SourceFile(UNKNOWN_FILE_NAME, UNKNOWN_FILE_NAME, Arrays.asList(lines));
    }
}