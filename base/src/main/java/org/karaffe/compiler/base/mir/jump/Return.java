package org.karaffe.compiler.base.mir.jump;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;

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
