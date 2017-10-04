package org.karaffe.compiler.tree.base;

import java.util.List;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.util.Position;

public interface Node {
    public String getID();

    public NodeType getNodeType();

    public Position getPosition();

    public List<? extends Node> getChildren();

    public void addChild(Node n);
}
