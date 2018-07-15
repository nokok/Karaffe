package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

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
