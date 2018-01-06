package org.karaffe.compiler.pos;

import java.util.Objects;
import java.util.Optional;

public class Range implements Position {

    private final String sourceName;
    private final Position begin;
    private final Position end;

    public Range(String sourceName, Position begin, Position end) {
        this.sourceName = sourceName;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String getLine() {
        return begin.getLine() + "-" + end.getLine();
    }

    @Override
    public Optional<Integer> getLineNumber() {
        return begin.getLineNumber();
    }

    @Override
    public String getCol() {
        return "";
    }

    @Override
    public Optional<Integer> getColNumber() {
        return begin.getColNumber();
    }

    @Override
    public String getSourceName() {
        return this.sourceName;
    }

    public Position begin() {
        return this.begin;
    }

    public Position end() {
        return this.end;
    }

    @Override
    public int compareTo(Position o) {
        Objects.requireNonNull(o);
        if (o.isNoPos()) {
            return 1;
        }
        if (o.isRange()) {
            Range other = o.asRange().get();
            int beginCompare = this.begin.compareTo(other.begin);
            if (beginCompare == 0) {
                return this.end.compareTo(other.end);
            }
            return beginCompare;
        }
        LineColPos other = (LineColPos) o;
        int beginVsOther = this.begin.compareTo(other);
        int otherVsEnd = other.compareTo(this.end);
        if (beginVsOther >= 0) {
            return beginVsOther;
        }
        if (otherVsEnd >= 0) {
            return otherVsEnd;
        }
        return 0;
    }

    @Override
    public Position merge(Position position) {
        if (position.isNoPos()) {
            return Position.copy(this);
        }
        if (!this.getSourceName().equals(position.getSourceName())) {
            Position.throwInvalidSourceNameException(this.sourceName, position.getSourceName());
        }
        final Position newBegin;
        final Position newEnd;
        if (position.isRange()) {
            Range other = (Range) position;
            newBegin = Position.small(this.begin, other.begin);
            newEnd = Position.large(this.end, other.end);
        } else {
            LineColPos other = (LineColPos) position;
            newBegin = Position.small(this.begin, other);
            newEnd = Position.large(this.end, other);
        }
        return new Range(this.sourceName, newBegin, newEnd);
    }

    @Override
    public String toString() {
        return String.format("%s:%s~%s:%s at %s", this.begin.getLine(), this.begin.getCol(), this.end.getLine(), this.end.getCol(), this.sourceName);
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((begin == null) ? 0 : begin.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
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
        if (!(obj instanceof Range)) {
            return false;
        }
        Range other = (Range) obj;
        if (begin == null) {
            if (other.begin != null) {
                return false;
            }
        } else if (!begin.equals(other.begin)) {
            return false;
        }
        if (end == null) {
            if (other.end != null) {
                return false;
            }
        } else if (!end.equals(other.end)) {
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
}
