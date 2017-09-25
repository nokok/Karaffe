package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.lexer.Token;

public class Literal extends TermNode {

    public Literal(final Token token) {
        super(token, NodeType.LITERAL);
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
