package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.base.AbstractNode;

public class Empty extends AbstractNode {

    public Empty() {
        this(Position.noPos());
    }

    public Empty(final Position position) {
        super(NodeType.EMPTY, position);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

    @Override
    public String toString() {
        return "()";
    }

    @Override
    public String vSource() {
        return "?";
    }
}
