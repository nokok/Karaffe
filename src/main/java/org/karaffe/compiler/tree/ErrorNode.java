package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.NodeD;
import org.karaffe.compiler.util.Position;

public class ErrorNode implements NodeD {

    private final Position position;

    public ErrorNode(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

}
