package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.LiteralToken;

public class Constant extends TermNode {

    public Constant(final LiteralToken constantToken) {
        super(NodeType.CONSTANT, constantToken);
    }

}
