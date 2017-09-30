package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.lexer.Token;

public class Modifier extends TermNode {

    public Modifier(final Token token) {
        super(NodeType.MODIFIER, token);
    }

    public boolean is(final Class<? extends ModifierToken> t) {
        return this.getToken().is(t);
    }

}
