package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.util.Position;

public interface Node {
    public String getID();

    public NodeType getNodeType();

    public Position getPosition();
}
