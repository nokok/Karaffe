package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Return extends AbstractNode {

    public Return(final Node expr) {
        super(NodeType.RETURN, expr);
    }

    public Node findExpr() {
        return this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final NodeList normalizedExpr = this.findExpr().normalize(context);
        nodes.addAll(normalizedExpr.flatten());
        nodes.add(new Return(normalizedExpr.lastAssignName()));
        return new NodeList(nodes);
    }

    @Override
    public String vSource() {
        return "return " + this.findExpr().vSource() + ";";
    }
}
