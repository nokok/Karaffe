package org.karaffe.compiler.tree;

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
}
