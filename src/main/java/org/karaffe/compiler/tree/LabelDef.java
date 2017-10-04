package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;

public class LabelDef extends AbstractNode {

    public LabelDef() {
        super(NodeType.DEFLABEL);
    }

}
