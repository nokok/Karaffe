package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class If extends AbstractNode {

    public If(final Node expr, final Node thenBlock) {
        super(NodeType.IF, expr, thenBlock);
    }

    public If(final Node expr, final Node thenBlock, final Node elseBlock) {
        super(NodeType.IF, expr, thenBlock, elseBlock);
    }

    public Node findCondExpr() {
        return this.getChildren().get(0);
    }

    public Node findThenBlock() {
        return this.getChildren().get(1);
    }

    public Optional<Node> findElseBlock() {
        if (this.getChildren().size() == 2) {
            return Optional.empty();
        }
        return Optional.of(this.getChildren().get(2));
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
