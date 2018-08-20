package org.karaffe.compiler.base.mir.instructions.jump;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class JumpTarget extends AbstractInstruction {
    private Label targetName;

    public JumpTarget(Label targetName) {
        this.targetName = Objects.requireNonNull(targetName);
    }

    public Label getTargetName() {
        return targetName;
    }

    @Override
    public String toString() {
        return "JumpTarget " + targetName;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.JUMPTARGET;
    }
}
