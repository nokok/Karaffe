package org.karaffe.compiler.frontend.karaffe.ast.api;

public interface Tree {
    default boolean isTermNode() {
        return false;
    }

    default boolean isNotTermNode() {
        return !this.isTermNode();
    }

    default boolean isNormalizable() {
        return false;
    }

    default boolean isNotNormalizable() {
        return !this.isNormalizable();
    }

    default void accept(ASTVisitor visitor) {

    }
}
