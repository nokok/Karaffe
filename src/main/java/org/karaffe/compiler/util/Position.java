package org.karaffe.compiler.util;

import java.util.Optional;
import java.util.function.Function;

public interface Position {

    public default boolean is(final Class<? extends Position> clazz) {
        return clazz.isInstance(this);
    }

    public default <R> R map(final Function<Position, R> func) {
        return func.apply(this);
    }

    public default <R> R match(final Function<NoPos, R> nFunc, final Function<LinePosition, R> lFunc, final Function<LineColumnPosition, R> lcFunc) {
        if (this.is(NoPos.class)) {
            return nFunc.apply((NoPos) this);
        } else if (this.is(LineColumnPosition.class)) {
            return lcFunc.apply((LineColumnPosition) this);
        } else if (this.is(LinePosition.class)) {
            return lFunc.apply((LinePosition) this);
        } else {
            throw new IllegalStateException("No Match Function");
        }
    }

    public default Optional<LinePosition> toLinePos() {
        if (this.is(LinePosition.class)) {
            return Optional.of((LinePosition) this);
        }
        return Optional.empty();
    }

    public default Optional<LineColumnPosition> toLineColumnPos() {
        if (this.is(LineColumnPosition.class)) {
            return Optional.of((LineColumnPosition) this);
        }
        return Optional.empty();
    }

    public default Optional<RangePosition> toRangePosition() {
        if (this.is(RangePosition.class)) {
            return Optional.of((RangePosition) this);
        }
        return Optional.empty();
    }

    public default Optional<Integer> getLineF() {
        return Optional.empty();
    }

    public default Optional<Integer> getColumnF() {
        return Optional.empty();
    }

    public default Optional<Integer> getEndLineF() {
        return Optional.empty();
    }

    public default Optional<Integer> getEndColumnF() {
        return Optional.empty();
    }

    public static Position noPos() {
        return new NoPos();
    }

    public static Position ofLine(final int line) {
        return new LinePositionImpl(line);
    }

    public static Position ofLineWithColumn(final int line, final int column) {
        return new LineColumnPositionImpl(line, column);
    }

    public static Position ofLineWithRangeColumn(final int line, final int startColumnIndex, final int endColumnIndex) {
        return new RangePositionImpl(line, startColumnIndex, endColumnIndex);
    }
}
