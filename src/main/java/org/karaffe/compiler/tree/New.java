package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class New extends AbstractNode {

	public New(final Node newInstanceTarget) {
		super(NodeType.NEW, newInstanceTarget);
	}

	@Override
	public void accept(KaraffeTreeVisitor visitor) {
		visitor.visit(this);
	}

	public Node newInstanceTarget() {
		return getChildren().get(0);
	}

	@Override
	public String vSource() {
		return "new " + newInstanceTarget().vSource();
	}

	@Override
	public NodeList normalize(NormalizeContext context) {
		List<Node> nodes = new ArrayList<>();
		Name name = context.nextName(nodes);
		nodes.add(new Assign(name, this));
		return new NodeList(nodes);
	}
}
