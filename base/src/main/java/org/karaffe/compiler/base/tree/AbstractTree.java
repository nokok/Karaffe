package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.pos.Position;

public abstract class AbstractTree implements Tree {
    private Position position;

    public void setPos(Position pos) {
        this.position = pos;
    }

    public Position getPos() {
        return this.position;
    }
}
