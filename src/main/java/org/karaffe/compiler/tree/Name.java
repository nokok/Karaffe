package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.IdentifierToken;

public class Name extends TermNode {

    public Name(final IdentifierToken token) {
        super(NodeType.NAME, token);
    }

    public Name(final String name) {
        this(new IdentifierToken.VarName(name));
    }
}
