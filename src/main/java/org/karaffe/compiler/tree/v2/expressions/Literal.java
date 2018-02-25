package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Term;

public abstract class Literal extends AbstractTree implements Expression, Term {
    public Literal() {
        super(Position.noPos());
    }

    public Literal(Position position) {
        super(position);
    }

}
