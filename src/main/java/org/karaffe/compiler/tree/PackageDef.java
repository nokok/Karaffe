package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.NodeType;
import org.karaffe.compiler.util.Position;

public class PackageDef extends AbstractNode {

    private final Select selector;

    public PackageDef(final Select selector) {
        this(Position.noPos(), selector);
    }

    public PackageDef(final Position position, final Select selector) {
        super(NodeType.DEFPACKAGE, position);
        this.selector = selector;
    }

    @Override
    public String toString() {
        return String.format("(PackageDecl %s)", this.selector);
    }

}
