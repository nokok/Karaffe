package org.karaffe.compiler.base.pos;

import java.util.Objects;
import java.util.Optional;

public class Range implements Position {

    private final String sourceName;
    private final Position begin;
    private final Position end;

    public Range(final String sourceName, final Position begin, final Position end) {
        this.sourceName = sourceName;
        this.begin = begin;
        this.end = end;
    }

    public Position begin() {
        return this.begin;
    }

    @Override
    public int compareTo(final Position o) {
        Objects.requireNonNull(o);
        if (o.isNoPos()) {
            return 1;
        }
        if (o.isRange()) {
            final Range other = o.asRange().get();
            final int beginCompare = this.begin.compareTo(other.begin);
            if (beginCompare == 0) {
                return this.end.compareTo(other.end);
            }
            return beginCompare;
        }
        final LineColPos other = (LineColPos) o;
        final int beginVsOther = this.begin.compareTo(other);
        final int otherVsEnd = other.compareTo(this.end);
        if (beginVsOther >= 0) {
            return beginVsOther;
        }
        if (otherVsEnd >= 0) {
            return otherVsEnd;
        }
        return 0;
    }

    public Position end() {
        return this.end;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Range)) {
            return false;
        }
        final Range other = (Range) obj;
        if (this.begin == null) {
            if (other.begin != null) {
                return false;
            }
        } else if (!this.begin.equals(other.begin)) {
            return false;
        }
        if (this.end == null) {
            if (other.end != null) {
                return false;
            }
        } else if (!this.end.equals(other.end)) {
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
        return "";
    }

    @Override
    public Optional<Integer> getColNumber() {
        return this.begin.getColNumber();
    }

    @Override
    public String getLine() {
        return this.begin.getLine() + "-" + this.end.getLine();
    }

    @Override
    public Optional<Integer> getLineNumber() {
        return this.begin.getLineNumber();
    }

    @Override
    public String getSourceName() {
        return this.sourceName;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.begin == null) ? 0 : this.begin.hashCode());
        result = (prime * result) + ((this.end == null) ? 0 : this.end.hashCode());
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
        final Position newBegin;
        final Position newEnd;
        if (position.isRange()) {
            final Range other = (Range) position;
            newBegin = Position.min(this.begin, other.begin);
            newEnd = Position.max(this.end, other.end);
        } else {
            final LineColPos other = (LineColPos) position;
            newBegin = Position.min(this.begin, other);
            newEnd = Position.max(this.end, other);
        }
        return new Range(this.sourceName, newBegin, newEnd);
    }

    @Override
    public String toString() {
        return String.format("%s:%s~%s:%s at %s", this.begin.getLine(), this.begin.getCol(), this.end.getLine(), this.end.getCol(), this.sourceName);
    }
}
