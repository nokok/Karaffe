package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.base.pos.Position;

public interface TreePosition {
    Position getPosition();

    void resetPosition(Position position);
}
