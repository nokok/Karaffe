package org.karaffe.compiler.base.mir.instructions.block;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.LabeledInstruction;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class BeginClass extends AbstractInstruction implements LabeledInstruction {
    private Label label;

    public BeginClass(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    @Override
    public Label getLabel() {
        return label;
    }

    public String getClassName() {
        return label.getSimpleName();
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.BEGINCLASS;
    }

    @Override
    public String toString() {
        return "BeginClass " + label;
    }

}
