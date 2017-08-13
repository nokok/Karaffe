package org.karaffe.compiler.util;

public class LineColumnPositionImpl implements LineColumnPosition {

    private final LinePosition line;
    private final int column;

    public LineColumnPositionImpl(final int line, final int column) {
        this.line = new LinePositionImpl(line);
        this.column = column;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public int getLine() {
        return this.line.getLine();
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.line, this.column);
    }

}
