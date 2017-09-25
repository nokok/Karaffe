package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.lexer.LiteralToken;

public class Constant extends TermNode {

    public Constant(final LiteralToken constantToken) {
        super(constantToken, NodeType.CONSTANT);
    }

    @Override
    public String toString() {
        return String.format("(Constant %s)", this.getText());
    }
}
