package org.karaffe.compiler.tree;

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

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

}
