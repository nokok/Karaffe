package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Assign extends AbstractNode {

    public Assign(final Node target, final Node expr) {
        super(NodeType.ASSIGN, target, expr);
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Node findExpr() {
        return this.getChildren().get(1);
    }

    public Name findTarget() {
        return (Name) this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final NodeList normalizedExpr = this.findExpr().normalize(context);
        nodes.addAll(normalizedExpr.flatten());
        nodes.add(new Assign(this.findTarget(), normalizedExpr.lastAssignName()));
        return new NodeList(nodes);

    }

    @Override
    public String vSource() {
        return String.format("%s = %s;", this.findTarget().vSource(), this.findExpr().vSource());
    }
}
