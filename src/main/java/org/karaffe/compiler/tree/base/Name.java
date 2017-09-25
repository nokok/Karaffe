package org.karaffe.compiler.tree.base;

import org.karaffe.compiler.lexer.IdentifierToken;

public class Name extends TermNode {

    public Name(final IdentifierToken token) {
        super(token, NodeType.VARNAME);
    }

    @Override
    public String toString() {
        return String.format("(Name %s)", this.getText());
    }
}
