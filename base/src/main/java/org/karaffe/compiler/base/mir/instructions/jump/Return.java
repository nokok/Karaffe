package org.karaffe.compiler.base.mir.instructions.jump;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;

public class Return extends AbstractInstruction {
    @Override
    public InstructionType getInstType() {
        return InstructionType.RETURN;
    }

    @Override
    public String toString() {
        return "Return";
    }
}
