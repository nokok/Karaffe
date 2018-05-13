package org.karaffe.compiler.base.util;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SourceFile {
    private final String fileName;
    private final String absolutePath;
    private final List<String> lines;

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

    public Path toPath() {
        return Paths.get(this.absolutePath);
    }
}