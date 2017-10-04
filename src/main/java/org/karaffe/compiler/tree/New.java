package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public class New extends AbstractNode {

    public New(final Node newInstanceTarget) {
        super(NodeType.NEW, newInstanceTarget);
    }

}
