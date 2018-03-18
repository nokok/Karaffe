package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.pos.Position;

public abstract class AbstractExpression extends AbstractTree implements Expression {
    public AbstractExpression() {

    }

    public AbstractExpression(Position position) {
        super(position);
    }
}
