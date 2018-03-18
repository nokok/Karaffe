package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.tree.base.Node;

public interface NamedDef extends Node {
    public default Name findNameNode() {
        return (Name) getChildren().get(1);
    }

    public default Optional<Node> findDefinitionTypeName() {
        Node node = this.getChildren().get(2);
        if (node.isEmptyNode()) {
            return Optional.empty();
        }
        return Optional.of(node);
    }

    public default Optional<Node> findInitializer() {
        if (this.getChildren().size() == 3) {
            return Optional.empty();
        }
        Node node = this.getChildren().get(3);
        if (node.isEmptyNode()) {
            return Optional.empty();
        }
        return Optional.of(node);
    }

    @Override
    default boolean isNamedDef() {
        return true;
    }

}
