package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

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

	@Override
	public NodeList normalize(NormalizeContext context) {
		return this.toNodeList();
	}
}
