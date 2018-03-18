package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractExpression;
import org.karaffe.compiler.tree.v2.api.Term;

public abstract class Literal extends AbstractExpression implements Term {

    public Literal() {
        super(Position.noPos());
    }

    public Literal(Position position) {
        super(position);
    }

}
