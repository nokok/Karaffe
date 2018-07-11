package org.karaffe.compiler.mir.jump;

import org.karaffe.compiler.mir.AbstractInstruction;
import org.karaffe.compiler.mir.util.Label;

import java.util.Objects;

public class Jump extends AbstractInstruction {
    private Label label;

    public Jump(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }
}
