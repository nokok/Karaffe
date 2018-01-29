package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public class Assign extends AbstractNode {

	public Assign(final Node target, final Node expr) {
		super(NodeType.ASSIGN, target, expr);
	}

	@Override
	public void accept(KaraffeTreeVisitor visitor) {
		visitor.visit(this);
	}

	public Node findTarget() {
		return getChildren().get(0);
	}

	public Node findExpr() {
		return getChildren().get(1);
	}

	@Override
	public String vSource() {
		return String.format("%s = %s;", findTarget().vSource(), findExpr().vSource());
	}

	@Override
	public NodeList normalize(NormalizeContext context) {
		List<Node> nodes = new ArrayList<>();
		NodeList normalizedExpr = this.findExpr().normalize(context);
		nodes.addAll(normalizedExpr.flatten());
		nodes.add(new Assign(this.findTarget(), normalizedExpr.lastAssignName()));
		return new NodeList(nodes);

	}
}
