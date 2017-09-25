package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.util.Position;

public class Empty extends AbstractNode {

    public Empty() {
        this(Position.noPos());
    }

    public Empty(final Position position) {
        super(NodeType.EMPTY, position);
    }

    @Override
    public String toString() {
        return "()";
    }
}
