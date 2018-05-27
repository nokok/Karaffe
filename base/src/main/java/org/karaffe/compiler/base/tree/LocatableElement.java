package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.pos.Position;

public interface LocatableElement {
    void setPos(Position pos);

    Position getPos();
}
