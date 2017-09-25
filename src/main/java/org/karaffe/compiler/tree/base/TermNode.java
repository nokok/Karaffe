package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.lexer.Token;

public abstract class TermNode extends AbstractNode {

    private final Token token;

    public TermNode(final Token token, final NodeType nodeType) {
        super(nodeType, token.getPosition());
        this.token = token;
    }

    public String getText() {
        return this.token.getText();
    }

    protected Token getToken() {
        return this.token;
    }
}
