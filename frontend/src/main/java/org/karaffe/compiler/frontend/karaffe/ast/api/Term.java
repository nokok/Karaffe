package org.karaffe.compiler.frontend.karaffe.ast.api;

public interface Term extends Tree {

    @Override
    public default boolean isNormalizable() {
        return false;
    }

    @Override
    public default boolean isTermNode() {
        return true;
    }
}
