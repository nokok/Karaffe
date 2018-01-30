package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Constant extends TermNode {

    public Constant(final LiteralToken constantToken) {
        super(NodeType.CONSTANT, constantToken);
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

}
