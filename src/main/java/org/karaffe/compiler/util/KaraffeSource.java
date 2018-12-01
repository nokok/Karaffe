package org.karaffe.compiler.util;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class KaraffeSource implements CharSequence {

    private final Path path;
    private final String source;

    private KaraffeSource(String source) {
        this.path = null;
        this.source = Objects.requireNonNull(source);
    }

    private KaraffeSource(Path path) throws IOException {
        Objects.requireNonNull(path);
        List<String> strings = Files.readAllLines(path);
        this.path = path;
        this.source = strings.stream().reduce((l, r) -> l + "\n" + r).orElse("");
    }

    public static KaraffeSource fromString(String source) {
        return new KaraffeSource(source);
    }

    public static KaraffeSource fromPath(Path path) throws IOException {
        return new KaraffeSource(path);
    }

    @Override
    public int length() {
        return this.source.length();
    }

    @Override
    public char charAt(int index) {
        return this.source.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.subSequence(start, end);
    }

    @Override
    public String toString() {
        return this.source;
    }

    public boolean hasFilePath() {
        return false;
    }

    public CharStream asCharStream() {
        if (this.path == null) {
            return CharStreams.fromString(this.toString());
        } else {
            try {
                return CharStreams.fromPath(path);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }
}
