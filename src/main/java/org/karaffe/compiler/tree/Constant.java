package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.lexer.LiteralToken;

public class Constant extends TermNode {

    public Constant(final LiteralToken constantToken) {
        super(NodeType.CONSTANT, constantToken);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

}
