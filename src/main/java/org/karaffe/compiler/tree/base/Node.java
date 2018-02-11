package org.karaffe.compiler.tree.base;

import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.types.InferResult;

public interface Node extends Cloneable {

    public void addChild(Node n);

    public List<? extends Node> getChildren();

    public String getID();

    public NodeType getNodeType();

    public Position getPosition();

    public default boolean hasAnyChild() {
        return !getChildren().isEmpty();
    }

    public default boolean isEmptyNode() {
        return this.getNodeType().equals(NodeType.EMPTY);
    }

    public default boolean isName() {
        return this.getNodeType().equals(NodeType.NAME);
    }

    public default boolean isNodeList() {
        return this instanceof NodeList;
    }

    public default boolean isNonEmptyNode() {
        return !this.isEmptyNode();
    }

    public default boolean isTermNode() {
        return false;
    }

    public default boolean isNamedDef() {
        return false;
    }

    public default boolean isAssign() {
        return this.getNodeType().equals(NodeType.ASSIGN);
    }

    public NodeList normalize(NormalizeContext context);

    public void replaceChildren(List<Node> replaced);

    public default NodeList toNodeList() {
        return new NodeList(this);
    }

    public default Optional<InferResult> tryTypeInference(final TypeContext context) {
        return Optional.empty();
    }

    public String vSource();
}
