package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

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

    public Optional<Node> findElseBlock() {
        if (this.getChildren().size() == 2) {
            return Optional.empty();
        }
        return Optional.of(this.getChildren().get(2));
    }

    public Node findThenBlock() {
        return this.getChildren().get(1);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final NodeList condExprList = this.findCondExpr().normalize(context);
        final NodeList thenBlockList = this.findThenBlock().normalize(context);
        final Optional<NodeList> mayBeElseBlockList = this.findElseBlock().map(n -> n.normalize(context));
        nodes.addAll(condExprList.flatten());
        final Node condExpr = condExprList.lastAssignName();
        final If ifExpr = mayBeElseBlockList
                .map(nodeList -> new If(condExpr, thenBlockList, nodeList))
                .orElse(new If(condExpr, thenBlockList));
        nodes.add(ifExpr);
        return new NodeList(nodes);
    }

    @Override
    public String vSource() {
        return String.format("if(%s){%s}else{%s}", this.findCondExpr().vSource(), this.findThenBlock().vSource(),
                this.findElseBlock().map(Node::vSource).orElse(""));
    }
}
