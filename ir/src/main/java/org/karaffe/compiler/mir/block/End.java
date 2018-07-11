package org.karaffe.compiler.mir.block;

import org.karaffe.compiler.mir.AbstractInstruction;
import org.karaffe.compiler.mir.Instruction;
import org.karaffe.compiler.mir.util.Label;

import java.util.Objects;

public class End extends AbstractInstruction {
    private Label label;

    public End(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }
}
