package org.karaffe.compiler.pos;

import java.util.Objects;
import java.util.Optional;

public class NoPos implements Position {

    @Override
    public String getLine() {
        return "?";
    }

    @Override
    public Optional<Integer> getLineNumber() {
        return Optional.empty();
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
    public String getSourceName() {
        return "?";
    }

    @Override
    public int compareTo(Position o) {
        Position other = Objects.requireNonNull(o);
        if (other.isNoPos()) {
            return 0;
        }
        return -1;
    }

    @Override
    public Position merge(Position position) {
        if (position.isNoPos()) {
            return Position.noPos();
        }
        return Position.of(position.getSourceName(), position.getLineNumber().get(), position.getColNumber().get());
    }

    @Override
    public String toString() {
        return "<no-pos>";
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof NoPos;
    }

    @Override
    public final int hashCode() {
        return 0;
    }

}