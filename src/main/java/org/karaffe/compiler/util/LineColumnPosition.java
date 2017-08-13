package org.karaffe.compiler.util;

import java.util.Optional;

public interface LineColumnPosition extends LinePosition {
    public int getColumn();

    @Override
    default Optional<Integer> getColumnF() {
        return Optional.of(this.getColumn());
    }
}
