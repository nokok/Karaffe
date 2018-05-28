package org.karaffe.compiler.base.pos;

import org.antlr.v4.runtime.Token;

import java.util.Objects;
import java.util.Optional;

public interface Position extends Comparable<Position> {

    public static Position copy(final Position that) {
        if (that.isNoPos()) {
            return Position.noPos();
        }
        if (that.isRange()) {
            final Range range = (Range) that;
            return new Range(range.getSourceName(), range.begin(), range.end());
        }
        final LineColPos pos = (LineColPos) that;
        return Position.of(pos.getSourceName(), pos.getLineRaw(), pos.getColumnRaw());

    }

    public static Position max(final Position left, final Position right) {
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

    public static Position min(final Position left, final Position right) {
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

    public static Position noPos() {
        return new NoPos();
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

    public static Position of(Token token) {
        Objects.requireNonNull(token);
        return of(token.getTokenSource().getSourceName(), token.getLine(), token.getCharPositionInLine());
    }

    public static Position ofRange(Token startToken, Token endToken) {
        Position startPosition = of(startToken);
        Position endPosition = of(endToken);
        return new Range(startPosition.getSourceName(), startPosition, endPosition);
    }

    public static void throwInvalidSourceNameException(final String thisSourceName, final String otherSourceName) {
        throw new IllegalArgumentException(String.format("The merge target \"SourceName\" different. %s vs %s", thisSourceName, otherSourceName));
    }

    public default Optional<Range> asRange() {
        if (this instanceof Range) {
            return Optional.of((Range) this);
        }
        return Optional.empty();
    }

    public String getCol();

    public Optional<Integer> getColNumber();

    public String getLine();

    public Optional<Integer> getLineNumber();

    public String getSourceName();

    public default boolean isNoPos() {
        return this instanceof NoPos;
    }

    public default boolean isRange() {
        return this instanceof Range;
    }

    public Position merge(Position position);

}
