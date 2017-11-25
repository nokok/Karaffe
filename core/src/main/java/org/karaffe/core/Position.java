package org.karaffe.core;

public class Position {
    private final int line;
    private final int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return this.line + ":" + this.column;
    }
}
