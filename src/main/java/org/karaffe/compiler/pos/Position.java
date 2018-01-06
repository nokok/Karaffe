package org.karaffe.compiler.pos;

import java.util.Optional;

public interface Position extends Comparable<Position> {

    public String getLine();

    public Optional<Integer> getLineNumber();

    public String getCol();

    public Optional<Integer> getColNumber();

    public String getSourceName();

    public Position merge(Position position);

    public default boolean isNoPos() {
        return this instanceof NoPos;
    }

    public default boolean isRange() {
        return this instanceof Range;
    }

    public default Optional<Range> asRange() {
        if (this instanceof Range) {
            return Optional.of((Range) this);
        }
        return Optional.empty();
    }

    public static Position noPos() {
        return new NoPos();
    }

    public static Position copy(Position that) {
        if (that.isNoPos()) {
            return Position.noPos();
        }
        if (that.isRange()) {
            Range range = (Range) that;
            return new Range(range.getSourceName(), range.begin(), range.end());
        }
        LineColPos pos = (LineColPos) that;
        return Position.of(pos.getSourceName(), pos.getLineRaw(), pos.getColumnRaw());

    }

    public static Position of(final String sourceName, final int line, final int column) {
        if (line <= 0) {
            throw new IllegalArgumentException("Line Number <= 0 :" + line);
        }
        if (column < 0) {
            throw new IllegalArgumentException("Col Number < 0 :" + column);
        }
        return new LineColPos(sourceName, line, column);
    }

    public static void throwInvalidSourceNameException(String thisSourceName, String otherSourceName) {
        throw new IllegalArgumentException(String.format("The merge target \"SourceName\" different. %s vs %s", thisSourceName, otherSourceName));
    }

    public static Position large(Position left, Position right) {
        if (left.isNoPos() && right.isNoPos()) {
            return left;
        }
        if (left.isNoPos()) {
            return Position.copy(right);
        }
        if (right.isNoPos()) {
            return Position.copy(left);
        }
        if (left.compareTo(right) < 0) {
            return Position.copy(right);
        }
        return Position.copy(left);
    }

    public static Position small(Position left, Position right) {
        if (left.isNoPos() && right.isNoPos()) {
            return left;
        }
        if (left.isNoPos()) {
            return Position.copy(right);
        }
        if (right.isNoPos()) {
            return Position.copy(left);
        }
        if (left.compareTo(right) <= 0) {
            return Position.copy(left);
        }
        return Position.copy(right);
    }

}
