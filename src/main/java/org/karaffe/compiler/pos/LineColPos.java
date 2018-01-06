package org.karaffe.compiler.pos;

import java.util.Optional;

public class LineColPos implements Position {
    private final String sourceName;
    private final int line;
    private final int column;

    public LineColPos(final String source, final int line, final int column) {
        this.sourceName = source;
        this.line = line;
        this.column = column;
    }

    public int getLineRaw() {
        return this.line;
    }

    public int getColumnRaw() {
        return this.column;
    }

    @Override
    public String getLine() {
        return String.valueOf(this.line);
    }

    @Override
    public Optional<Integer> getLineNumber() {
        return Optional.of(this.line);
    }

    @Override
    public String getCol() {
        return String.valueOf(this.column);
    }

    @Override
    public Optional<Integer> getColNumber() {
        return Optional.of(this.column);
    }

    @Override
    public String getSourceName() {
        return Optional.ofNullable(this.sourceName).orElse("?");
    }

    @Override
    public Position merge(Position position) {
        if (position.isNoPos()) {
            return Position.copy(this);
        }
        if (!this.getSourceName().equals(position.getSourceName())) {
            Position.throwInvalidSourceNameException(this.sourceName, position.getSourceName());
        }
        if (position.isRange()) {
            Range other = position.asRange().get();
            Position thisOther = new Range(this.sourceName, position, other);
            return thisOther;
        }
        LineColPos other = (LineColPos) position;
        Position thisOther = new Range(this.sourceName, Position.copy(this), Position.copy(position));
        Position otherThis = new Range(this.sourceName, Position.copy(position), Position.copy(this));
        if (this.line < other.line) {
            return thisOther;
        } else if (this.line == other.line) {
            if (this.column < other.column) {
                return thisOther;
            }
            return otherThis;
        }
        return otherThis;

    }

    @Override
    public String toString() {
        return String.format("%d:%d at %s", this.line, this.column, this.sourceName);
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + line;
        result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LineColPos)) {
            return false;
        }
        LineColPos other = (LineColPos) obj;
        if (column != other.column) {
            return false;
        }
        if (line != other.line) {
            return false;
        }
        if (sourceName == null) {
            if (other.sourceName != null) {
                return false;
            }
        } else if (!sourceName.equals(other.sourceName)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Position o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o.isNoPos()) {
            return 1;
        }
        if (o.isRange()) {
            Range other = o.asRange().get();
            int thisVsBegin = this.compareTo(other.begin());
            int endVsThis = other.end().compareTo(this);
            if (thisVsBegin < 0) {
                return thisVsBegin;
            }
            if (endVsThis < 0) {
                return endVsThis;
            }
            return 0;
        }
        LineColPos other = (LineColPos) o;
        if (this.line == other.line) {
            if (this.column == other.column) {
                return 0;
            }
            return this.column - other.column;
        }
        return this.line - other.line;
    }

}