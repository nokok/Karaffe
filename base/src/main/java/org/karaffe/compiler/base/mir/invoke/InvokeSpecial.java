package org.karaffe.compiler.base.mir.invoke;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class InvokeSpecial extends AbstractInstruction {

    private Label label;

    public InvokeSpecial(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.INVOKESPECIAL;
    }

    @Override
    public String toString() {
        return "InvokeSpecial " + this.label;
    }
}
