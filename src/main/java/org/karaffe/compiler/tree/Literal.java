package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.KNormalizable;

public class Literal extends TermNode implements KNormalizable {

    public Literal(final Token token) {
        super(NodeType.LITERAL, token);
    }

}
