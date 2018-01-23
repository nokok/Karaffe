package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.base.AbstractNode;

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

    @Override
    public String toString() {
        return String.format("(%s %s)", this.getClass().getSimpleName(), this.token.getText());
    }

    @Override
    public String vSource() {
        return getText();
    }
}
