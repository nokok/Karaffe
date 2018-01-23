package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

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
}
