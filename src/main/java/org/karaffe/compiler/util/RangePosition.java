package org.karaffe.compiler.util;

import java.util.Optional;

public interface RangePosition extends LineColumnPosition {

    public int getEndLine();

    public int getEndColumn();

    @Override
    default Optional<Integer> getEndLineF() {
        return Optional.of(this.getEndLine());
    }

    @Override
    default Optional<Integer> getEndColumnF() {
        return Optional.of(this.getEndColumn());
    }

}
