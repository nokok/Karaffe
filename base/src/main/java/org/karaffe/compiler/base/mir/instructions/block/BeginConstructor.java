package org.karaffe.compiler.base.mir.instructions.block;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class BeginConstructor extends AbstractInstruction {

    private Label label;

    public BeginConstructor(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }

    public String getParameters() {
        return "";
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.BEGINCONSTRUCTOR;
    }

    @Override
    public String toString() {
        return "BeginConstructor " + label;
    }
}
