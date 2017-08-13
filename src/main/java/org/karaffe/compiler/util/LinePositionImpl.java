package org.karaffe.compiler.util;

public class LinePositionImpl implements LinePosition {

    private final int line;

    LinePositionImpl(final int line) {
        this.line = line;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public String toString() {
        return String.valueOf(this.line);
    }

}
