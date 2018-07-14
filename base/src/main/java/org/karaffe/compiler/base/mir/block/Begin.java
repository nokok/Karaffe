package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class Begin extends AbstractInstruction {
    private Label label;

    public Begin(InstructionType type, Label label) {
        this.setInstType(type);
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Begin " + getInstType() + " " + label;
    }
}
