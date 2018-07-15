package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class EndConstructor extends AbstractInstruction {

    private Label label;

    public EndConstructor(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.ENDCONSTRUCTOR;
    }

    @Override
    public String toString() {
        return "EndConstructor " + label;
    }
}
