package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.api.AbstractExpression;
import org.karaffe.compiler.ast.api.Term;

public abstract class Literal extends AbstractExpression implements Term {

    public Literal() {
        super(Position.noPos());
    }

    public Literal(Position position) {
        super(position);
    }

}
