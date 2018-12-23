package org.karaffe.compiler.util;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class KaraffeSource implements CharSequence {

    private final String sourceName;
    private final String source;
    private final List<String> lines;
    private final CharStream charStream;

    private KaraffeSource(String source) {
        this.sourceName = "<unknown>";
        this.source = Objects.requireNonNull(source);
        this.lines = Arrays.asList(source.split("\r\n|[\n\r\u2028\u2029\u0085]")); //java.util.Scanner#LINE_SEPARATOR_PATTERN
        this.charStream = CharStreams.fromString(source);
    }

    private KaraffeSource(Path path) throws IOException {
        Objects.requireNonNull(path);
        List<String> strings = Files.readAllLines(path);
        this.sourceName = path.toString();
        this.source = strings.stream().reduce((l, r) -> l + "\n" + r).orElse("");
        this.lines = Arrays.asList(source.split("\r\n|[\n\r\u2028\u2029\u0085]")); //java.util.Scanner#LINE_SEPARATOR_PATTERN
        this.charStream = CharStreams.fromPath(path);
    }

    public static KaraffeSource fromString(String source) {
        return new KaraffeSource(source);
    }

    public static KaraffeSource fromPath(Path path) throws IOException {
        return new KaraffeSource(path);
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public String getCodeByLine(int line) {
        return this.lines.get(line - 1);
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

    public CharStream asCharStream() {
        return this.charStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KaraffeSource that = (KaraffeSource) o;
        return sourceName.equals(that.sourceName) &&
                source.equals(that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceName, source);
    }
}
