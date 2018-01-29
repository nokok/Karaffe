package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public class Constant extends TermNode {

    public Constant(final LiteralToken constantToken) {
        super(NodeType.CONSTANT, constantToken);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

	@Override
	public NodeList normalize(NormalizeContext context) {
		return this.toNodeList();
	}

}
