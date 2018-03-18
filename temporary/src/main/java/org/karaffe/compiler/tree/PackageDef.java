package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;

public class PackageDef extends AbstractNode {

    private final Select packageName;
    private final List<TypeDef> typeDefs = new ArrayList<>();

    public PackageDef(final Select selector) {
        super(NodeType.DEFPACKAGE, selector);
        this.packageName = selector;
    }

    public Select findSelector() {
        return this.packageName;
    }

    public String getRawPackageName() {
        return this.findSelector().toString(".");
    }

    public void addTypeDef(TypeDef type) {
        this.typeDefs.add(type);
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
