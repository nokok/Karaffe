package org.karaffe.compiler.frontend.karaffe.ast.api;

public interface Term extends Tree {

    @Override
    default boolean isNormalizable() {
        return false;
    }

    @Override
    default boolean isTermNode() {
        return true;
    }
}
