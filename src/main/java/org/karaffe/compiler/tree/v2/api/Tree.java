package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.types.v2.TypeConstraint;
import org.karaffe.compiler.types.v2.TypeConstraints;

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

    public default TypeConstraint getTypeConstraint() {
        return TypeConstraints.voidType();
    }
}
