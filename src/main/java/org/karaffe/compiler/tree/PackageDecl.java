package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.NodeD;
import org.karaffe.compiler.util.Position;

public class PackageDecl implements NodeD {
    private final Position position;
    private final Selector selector;

    public PackageDecl(final Selector selector) {
        this(Position.noPos(), selector);
    }

    public PackageDecl(final Position position, final Selector selector) {
        this.position = position;
        this.selector = selector;
    }

    @Override
    public String toString() {
        return String.format("(PackageDecl %s %s)", this.position, this.selector);
    }

}
