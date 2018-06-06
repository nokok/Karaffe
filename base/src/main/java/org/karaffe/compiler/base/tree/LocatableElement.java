package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.pos.Position;

public interface LocatableElement {
    Position getPos();

    void setPos(Position pos);
}
