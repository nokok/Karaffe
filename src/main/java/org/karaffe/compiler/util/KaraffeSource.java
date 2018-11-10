package org.karaffe.compiler.util;

import java.util.Objects;

public class KaraffeSource implements CharSequence {

    private final String source;

    private KaraffeSource(String source) {
        this.source = Objects.requireNonNull(source);
    }

    public static KaraffeSource fromString(String source) {
        return new KaraffeSource(source);
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
}
