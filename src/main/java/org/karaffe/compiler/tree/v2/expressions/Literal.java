package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;

public abstract class Literal extends AbstractTree implements Expression {
    public Literal() {
        super(Position.noPos());
    }

    public Literal(Position position) {
        super(position);
    }
}
