package org.karaffe.compiler.util;

import java.util.Optional;

public interface LinePosition extends Position {

    public int getLine();

    @Override
    default Optional<Integer> getLineF() {
        return Optional.of(this.getLine());
    }
}
