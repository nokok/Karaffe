package org.karaffe.compiler.tree.meta;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public abstract class AbstractMetaNode extends AbstractNode implements MetaNode {

    public AbstractMetaNode() {
        super(NodeType.META);
    }

    public AbstractMetaNode(Node... children) {
        super(NodeType.META, children);
    }

    @Override
    public NodeList normalize(NormalizeContext context) {
        return this.toNodeList();
    }

}
