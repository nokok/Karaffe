package org.karaffe.compiler.base.pos;

import java.util.Objects;
import java.util.Optional;

public class NoPos implements Position {

    @Override
    public int compareTo(final Position o) {
        final Position other = Objects.requireNonNull(o);
        if (other.isNoPos()) {
            return 0;
        }
        return -1;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof NoPos;
    }

    @Override
    public String getCol() {
        return "?";
    }

    @Override
    public Optional<Integer> getColNumber() {
        return Optional.empty();
    }

    @Override
    public String getLine() {
        return "?";
    }

    @Override
    public Optional<Integer> getLineNumber() {
        return Optional.empty();
    }

    @Override
    public String getSourceName() {
        return "?";
    }

    @Override
    public final int hashCode() {
        return 0;
    }

    @Override
    public Position merge(final Position position) {
        if (position.isNoPos()) {
            return Position.noPos();
        }
        return Position.of(position.getSourceName(), position.getLineNumber().get(), position.getColNumber().get());
    }

    @Override
    public String toString() {
        return "<no-pos>";
    }

}