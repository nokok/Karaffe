package org.karaffe.compiler.pos;

import java.util.Optional;

public abstract class Pos {
    public static Pos noPos() {
        return new NoPos();
    }

    public static Pos fromLineAndColumn(int line, int column) {
        return new LineColumnPosition(line, column);
    }

    public static Pos range(int startIndex, int endIndex) {
        return new Range(startIndex, endIndex);
    }

    public static Pos rangeLocation(String filePath, int startIndex, int endIndex) {
        return new RangeLocation(filePath, startIndex, endIndex);
    }

    public static Pos location(String filePath, int line, int column) {
        return new Location(filePath, line, column);
    }

    public Optional<NoPos> asNoPos() {
        return this.as(NoPos.class);
    }

    public Optional<LineColumnPosition> asLineAndColumn() {
        return this.as(LineColumnPosition.class);
    }

    public Optional<Location> asLocation() {
        return this.as(Location.class);
    }

    private <T extends Pos> Optional<T> as(Class<T> clazz) {
        if (clazz.isInstance(this)) {
            return Optional.of(clazz.cast(this));
        }
        return Optional.empty();
    }
}

class NoPos extends Pos {
    @Override
    public String toString() {
        return "<no-pos>";
    }
}

class LineColumnPosition extends Pos {
    private final int line;
    private final int column;

    public LineColumnPosition(int line, int column) {
        if (line < 1) {
            throw new IllegalArgumentException("line number < 1 :" + line);
        }
        if (column < 0) {
            throw new IllegalArgumentException("column index is negative. :" + column);
        }
        this.line = line;
        this.column = column;
    }

    public int line() {
        return this.line;
    }

    public int column() {
        return this.column;
    }

    @Override
    public String toString() {
        return this.line + ":" + this.column;
    }
}

class Location extends Pos {
    private final String filePath;
    private final LineColumnPosition position;

    public Location(String filePath, int line, int column) {
        this.filePath = filePath;
        this.position = new LineColumnPosition(line, column);
    }

    @Override
    public String toString() {
        return this.filePath + ":" + this.position.toString();
    }
}

class Range extends Pos {
    private int start;
    private int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.start + "~" + this.end;
    }
}

class RangeLocation extends Pos {
    private final String filePath;
    private final Range range;

    public RangeLocation(String filePath, int start, int end) {
        this.filePath = filePath;
        this.range = new Range(start, end);
    }

    @Override
    public String toString() {
        return this.filePath + ":" + this.range.toString();
    }
}