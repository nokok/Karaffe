package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Return extends AbstractNode {

    public Return(Node expr) {
        super(NodeType.RETURN, expr);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Node findExpr() {
        return getChildren().get(0);
    }

    @Override
    public String vSource() {
        return "return " + findExpr().vSource() + ";";
    }

	@Override
	public NodeList normalize(NormalizeContext context) {
		List<Node> nodes = new ArrayList<>();
		NodeList normalizedExpr = findExpr().normalize(context);
		nodes.addAll(normalizedExpr.flatten());
		nodes.add(new Return(normalizedExpr.lastAssignName()));
		return new NodeList(nodes);
	}
}
