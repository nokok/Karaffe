package org.karaffe.compiler.base.mir.instructions.jump;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class Jump extends AbstractInstruction {
    private Label label;

    public Jump(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Jump " + label;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.JUMP;
    }
}
