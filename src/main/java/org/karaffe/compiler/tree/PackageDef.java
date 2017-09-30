package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;

public class PackageDef extends AbstractNode {

    public PackageDef(final Select selector) {
        super(NodeType.DEFPACKAGE, selector);
    }

}
