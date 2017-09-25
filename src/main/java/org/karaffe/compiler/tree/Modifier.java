package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.NodeType;
import org.karaffe.compiler.tree.base.TermNode;

public class Modifier extends TermNode {

    public Modifier(final Token token) {
        super(token, NodeType.MODIFIER);
    }

    public boolean is(final Class<? extends ModifierToken> t) {
        return this.getToken().is(t);
    }

}
