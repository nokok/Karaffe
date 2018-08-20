package org.karaffe.compiler.base.mir.instructions.jump;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class IfJumpTrue extends AbstractInstruction {
    private Label jumpTarget;

    public IfJumpTrue(Label jumpTarget) {
        this.jumpTarget = Objects.requireNonNull(jumpTarget);
    }

    @Override
    public String toString() {
        return "IfJumpTrue " + jumpTarget;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.IFJUMPTRUE;
    }
}
