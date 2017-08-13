package org.karaffe.compiler.util;

public class RangePositionImpl implements RangePosition {

    private final Position start;
    private final Position end;

    public RangePositionImpl(final int line, final int startColumnIndex, final int endColumnIndex) {
        this.start = Position.ofLineWithColumn(line, startColumnIndex);
        this.end = Position.ofLineWithColumn(line, endColumnIndex);
    }

    @Override
    public int getColumn() {
        return this.start.match(n -> {
            throw new IllegalStateException();
        }, n -> {
            throw new IllegalStateException();
        }, n -> {
            return n.getColumn();
        });
    }

    @Override
    public int getLine() {
        return this.start.match(n -> {
            throw new IllegalStateException();
        }, n -> {
            return n.getLine();
        }, n -> {
            return n.getLine();
        });
    }

    @Override
    public int getEndLine() {
        return this.end.match(n -> {
            throw new IllegalStateException();
        }, n -> {
            return n.getLine();
        }, n -> {
            return n.getLine();
        });
    }

    @Override
    public int getEndColumn() {
        return this.end.match(n -> {
            throw new IllegalStateException();
        }, n -> {
            throw new IllegalStateException();
        }, n -> {
            return n.getColumn();
        });
    }

    @Override
    public String toString() {
        return String.format("%s:%s-%s", this.getLine(), this.getColumn(), this.getEndColumn());
    }

}
