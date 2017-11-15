package org.karaffe.compiler.util;

import java.util.Optional;

public interface Position {

    public int getStartIndex();

    public int getEndIndex();

    public boolean hasSource();

    public String getSource();

    public default boolean is(final Class<? extends Position> clazz) {
        return clazz.isInstance(this);
    }

    public default Optional<Integer> getLineF() {
        return Optional.empty();
    }

    public default Optional<Integer> getColumnF() {
        return Optional.empty();
    }

    public static Position noPos() {
        return new NoPos();
    }

    public static Position of(final String sourceName, final int start, final int end, final int line, final int column) {
        return new RangeSource(sourceName, start, end, line, column);
    }

    public static Position of(final Position start, final Position end) {
        if (start.hasSource()) {
            return Position.of(start.getSource(), start.getStartIndex(), end.getEndIndex(), start.getLineF().orElse(-1), start.getColumnF().orElse(-1));
        }
        return new Range(start.getStartIndex(), end.getEndIndex());
    }

    public static class NoPos implements Position {

        @Override
        public String toString() {
            return "<no-pos>";
        }

        @Override
        public int getStartIndex() {
            return 0;
        }

        @Override
        public int getEndIndex() {
            return 0;
        }

        @Override
        public boolean hasSource() {
            return false;
        }

        @Override
        public String getSource() {
            return "<no-source>";
        }
    }

    public static class Range implements Position {
        private final int start;
        private final int end;

        public Range(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int getStartIndex() {
            return this.start;
        }

        @Override
        public int getEndIndex() {
            return this.end;
        }

        @Override
        public boolean hasSource() {
            return false;
        }

        @Override
        public String getSource() {
            return "<no-source>";
        }
    }

    public static class RangeSource implements Position {
        private final String source;
        private final Range range;
        private final int line;
        private final int column;

        public RangeSource(final String source, final int start, final int end, final int line, final int column) {
            this.source = source;
            this.range = new Range(start, end);
            this.line = line;
            this.column = column;
        }

        @Override
        public int getStartIndex() {
            return this.range.getStartIndex();
        }

        @Override
        public int getEndIndex() {
            return this.range.getEndIndex();
        }

        @Override
        public Optional<Integer> getColumnF() {
            return Optional.of(this.column);
        }

        @Override
        public Optional<Integer> getLineF() {
            return Optional.of(this.line);
        }

        @Override
        public boolean hasSource() {
            return true;
        }

        @Override
        public String getSource() {
            return this.source;
        }

        @Override
        public String toString() {
            return String.format("%s:%s at %s", this.getStartIndex(), this.getEndIndex(), this.getSource());
        }
    }
}
