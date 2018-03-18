package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class Goto extends AbstractNode {

    public Goto(final Node to) {
        super(NodeType.GOTO, to);
    }

    public Node findJumpTo() {
        return this.getChildren().get(0);
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

    @Override
    public String vSource() {
        return "goto " + this.findJumpTo().vSource();
    }
}
