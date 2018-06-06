package org.karaffe.compiler.base.pos;

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

    @Override
    public int compareTo(final Position o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o.isNoPos()) {
            return 1;
        }
        if (o.isRange()) {
            final Range other = o.asRange().get();
            final int thisVsBegin = this.compareTo(other.begin());
            final int endVsThis = other.end().compareTo(this);
            if (thisVsBegin < 0) {
                return thisVsBegin;
            }
            if (endVsThis < 0) {
                return endVsThis;
            }
            return 0;
        }
        final LineColPos other = (LineColPos) o;
        if (this.line == other.line) {
            if (this.column == other.column) {
                return 0;
            }
            return this.column - other.column;
        }
        return this.line - other.line;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LineColPos)) {
            return false;
        }
        final LineColPos other = (LineColPos) obj;
        if (this.column != other.column) {
            return false;
        }
        if (this.line != other.line) {
            return false;
        }
        if (this.sourceName == null) {
            if (other.sourceName != null) {
                return false;
            }
        } else if (!this.sourceName.equals(other.sourceName)) {
            return false;
        }
        return true;
    }

    @Override
    public String getCol() {
        return String.valueOf(this.column);
    }

    @Override
    public Optional<Integer> getColNumber() {
        return Optional.of(this.column);
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

    public int getLineRaw() {
        return this.line;
    }

    @Override
    public String getSourceName() {
        return Optional.ofNullable(this.sourceName).orElse("?");
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.column;
        result = (prime * result) + this.line;
        result = (prime * result) + ((this.sourceName == null) ? 0 : this.sourceName.hashCode());
        return result;
    }

    @Override
    public Position merge(final Position position) {
        if (position.isNoPos()) {
            return Position.copy(this);
        }
        if (!this.getSourceName().equals(position.getSourceName())) {
            Position.throwInvalidSourceNameException(this.sourceName, position.getSourceName());
        }
        if (position.isRange()) {
            final Range other = position.asRange().get();
            final Position thisOther = new Range(this.sourceName, position, other);
            return thisOther;
        }
        final LineColPos other = (LineColPos) position;
        final Position thisOther = new Range(this.sourceName, Position.copy(this), Position.copy(position));
        final Position otherThis = new Range(this.sourceName, Position.copy(position), Position.copy(this));
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
        return String.format("%d:%d:%s", this.line, this.column, this.sourceName);
    }

}