package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.util.Position;

public abstract class TermNode extends AbstractNode {

    private final Token token;

    public TermNode(final NodeType nodeType, final Token token) {
        super(nodeType, token.getPosition());
        this.token = token;
    }

    @Override
    public Position getPosition() {
        return this.token.getPosition();
    }

    public String getText() {
        return this.token.getText();
    }

    protected Token getToken() {
        return this.token;
    }
}
