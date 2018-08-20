package org.karaffe.compiler.base.mir.instructions.block;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class EndMethod extends AbstractInstruction {

    private Label label;

    public EndMethod(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.ENDMETHOD;
    }

    @Override
    public String toString() {
        return "EndMethod " + label;
    }
}
