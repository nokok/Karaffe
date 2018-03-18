package org.karaffe.compiler.tree.v2.api;

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
