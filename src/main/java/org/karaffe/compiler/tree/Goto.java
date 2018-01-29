package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public class Goto extends AbstractNode {

    public Goto(final Node to) {
        super(NodeType.GOTO, to);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Node findJumpTo() {
        return getChildren().get(0);
    }

    @Override
    public String vSource() {
        return "goto " + findJumpTo().vSource();
    }

	@Override
	public NodeList normalize(NormalizeContext context) {
		return this.toNodeList();
	}
}
