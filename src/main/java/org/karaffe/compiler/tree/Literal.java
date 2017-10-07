package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;

public class Literal extends TermNode {

    public Literal(final Token token) {
        super(NodeType.LITERAL, token);
    }

}
