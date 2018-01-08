package org.karaffe.compiler.tree;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

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

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
