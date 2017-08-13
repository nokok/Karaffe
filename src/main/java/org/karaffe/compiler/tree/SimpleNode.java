package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.NodeD;

public class SimpleNode implements NodeD {
    private final Token token;

    protected SimpleNode(final Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

}
