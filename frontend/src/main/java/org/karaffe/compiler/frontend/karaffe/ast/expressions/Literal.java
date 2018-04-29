package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractExpression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Term;

public abstract class Literal extends AbstractExpression implements Term {

    public Literal() {
        super(Position.noPos());
    }

    public Literal(Position position) {
        super(position);
    }

}
