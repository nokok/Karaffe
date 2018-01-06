package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.KNormalizable;
import org.karaffe.compiler.tree.base.Node;

public class Assign extends AbstractNode implements KNormalizable {

    public Assign(final Node target, final Node expr) {
        super(NodeType.ASSIGN, target, expr);
    }

    @Override
    public NodeList normalize() {
        return this.toNodeList();
    }

}
