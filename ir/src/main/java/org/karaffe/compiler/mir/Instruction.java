package org.karaffe.compiler.mir;

import org.karaffe.compiler.base.pos.Position;

public interface Instruction {
    InstructionType getInstType();

    void setInstType(InstructionType type);

    Position getPosition();

    void setPosition(Position position);
}
