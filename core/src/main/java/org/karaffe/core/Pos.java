package org.karaffe.core;

import java.util.Optional;

public abstract class Pos {
    public static Pos noPos() {
        return new NoPos();
    }

    public static Pos fromLineAndColumn(int line, int column) {
        return new LineColumnPosition(line, column);
    }

    public Optional<NoPos> asNoPos() {
        return this.as(NoPos.class);
    }

    public Optional<LineColumnPosition> asLineAndColumn() {
        return this.as(LineColumnPosition.class);
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
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return this.line + ":" + this.column;
    }
}
