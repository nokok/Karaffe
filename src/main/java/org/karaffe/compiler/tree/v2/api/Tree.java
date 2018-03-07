package org.karaffe.compiler.tree.v2.api;

public interface Tree {
    public default boolean isTermNode() {
        return false;
    }

    public default boolean isNotTermNode() {
        return !this.isTermNode();
    }

    public default boolean isNormalizable() {
        return false;
    }

    public default boolean isNotNormalizable() {
        return !this.isNormalizable();
    }

}
