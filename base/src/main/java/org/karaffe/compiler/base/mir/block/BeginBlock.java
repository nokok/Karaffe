package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.LabeledInstruction;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class BeginBlock extends AbstractInstruction implements LabeledInstruction {

    private Label label;

    public BeginBlock(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    @Override
    public Label getLabel() {
        return label;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.BEGINBLOCK;
    }

    @Override
    public String toString() {
        return "BeginBlock " + label;
    }
}
