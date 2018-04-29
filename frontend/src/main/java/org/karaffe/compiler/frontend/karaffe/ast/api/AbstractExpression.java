package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.base.pos.Position;

public abstract class AbstractExpression extends AbstractTree implements Expression {
    public AbstractExpression() {

    }

    public AbstractExpression(Position position) {
        super(position);
    }
}
