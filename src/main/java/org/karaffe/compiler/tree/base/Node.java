package org.karaffe.compiler.tree.base;

import java.util.List;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.TermNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public interface Node extends Cloneable {
    public String getID();

    public NodeType getNodeType();

    public Position getPosition();

    public default boolean hasAnyChild() {
        return !getChildren().isEmpty();
    }

    public void replaceChildren(List<Node> replaced);

    public List<? extends Node> getChildren();

    public void addChild(Node n);

    public default boolean isKNormalizable() {
        return this instanceof KNormalizable;
    }

    public default boolean isTermNode() {
        return this instanceof TermNode;
    }

    public default boolean isName() {
        return this instanceof Name;
    }

    public default boolean isNodeList() {
        return this instanceof NodeList;
    }

    public default NodeList toNodeList() {
        return new NodeList(this);
    }

    public void accept(KaraffeTreeVisitor visitor);
}
