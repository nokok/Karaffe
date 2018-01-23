package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class PackageDef extends AbstractNode {

    public PackageDef(final Select selector) {
        super(NodeType.DEFPACKAGE, selector);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Select findSelector() {
        return (Select) getChildren().get(0);
    }

    public String getRawPackageName() {
        return this.findSelector().toString(".");
    }

    @Override
    public String vSource() {
        return "package " + getRawPackageName() + ";";
    }
}
