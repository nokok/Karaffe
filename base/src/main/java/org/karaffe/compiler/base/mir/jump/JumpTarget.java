package org.karaffe.compiler.base.mir.jump;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class JumpTarget extends AbstractInstruction {
    private Label targetName;

    public JumpTarget(Label targetName) {
        this.targetName = Objects.requireNonNull(targetName);
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
