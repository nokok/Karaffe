package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;

public class Identifier extends SimpleNode {

    public Identifier(final Token token) {
        super(token);
    }

    public String getName() {
        return super.getToken().getText();
    }
}
