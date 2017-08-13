package org.karaffe.compiler.tree.base;

import java.util.Optional;

import org.karaffe.compiler.util.Position;

public interface Node {
    public String getID();

    public NodeType getNodeType();

    public Position getPosition();

    public Optional<Node> getChildNode();

    public Optional<Node> getNextNode();
}
