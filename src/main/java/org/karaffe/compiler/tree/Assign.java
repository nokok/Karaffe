package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.KNormalizable;
import org.karaffe.compiler.tree.base.Node;

public class Assign extends AbstractNode {

    public Assign(final Node target, final Node expr) {
        super(NodeType.ASSIGN, target, expr);
    }

}
