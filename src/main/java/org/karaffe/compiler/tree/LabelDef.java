package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class LabelDef extends AbstractNode {

    public LabelDef() {
        super(NodeType.DEFLABEL);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String vSource() {
        return String.format("Label(%s):", this.hashCode());
    }
}
