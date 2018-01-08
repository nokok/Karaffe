package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.KNormalizable;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Literal extends TermNode implements KNormalizable {

    public Literal(final Token token) {
        super(NodeType.LITERAL, token);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
