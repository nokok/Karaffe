package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;

public class PackageDef extends AbstractNode {

    public PackageDef(final Select selector) {
        super(NodeType.DEFPACKAGE, selector);
    }

    public Select findSelector() {
        return (Select) this.getChildren().get(0);
    }

    public String getRawPackageName() {
        return this.findSelector().toString(".");
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        return this.toNodeList();
    }

    @Override
    public String vSource() {
        return "package " + this.getRawPackageName() + ";";
    }
}
