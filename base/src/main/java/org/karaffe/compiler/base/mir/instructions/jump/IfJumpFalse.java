package org.karaffe.compiler.base.mir.instructions.jump;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class IfJumpFalse extends AbstractInstruction {
    private Label jumpTarget;

    public IfJumpFalse(Label jumpTarget) {
        this.jumpTarget = Objects.requireNonNull(jumpTarget);
    }

    public Label getJumpTarget() {
        return jumpTarget;
    }

    @Override
    public String toString() {
        return "IfJumpFalse " + jumpTarget;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.IFJUMPFALSE;
    }
}
