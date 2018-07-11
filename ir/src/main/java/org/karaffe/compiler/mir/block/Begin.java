package org.karaffe.compiler.mir.block;

import org.karaffe.compiler.mir.AbstractInstruction;
import org.karaffe.compiler.mir.util.Label;

import java.util.Objects;

public class Begin extends AbstractInstruction {
    private BlockType type;
    private Label label;

    public Begin(BlockType type, Label label) {
        this.type = Objects.requireNonNull(type);
        this.label = Objects.requireNonNull(label);
    }

    public BlockType getType() {
        return type;
    }

    public Label getLabel() {
        return label;
    }
}
