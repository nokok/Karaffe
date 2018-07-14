package org.karaffe.compiler.mir.jump;

import org.karaffe.compiler.mir.AbstractInstruction;
import org.karaffe.compiler.mir.util.Label;

import java.util.Objects;

public class JumpTarget extends AbstractInstruction {
    private Label targetName;

    public JumpTarget(Label targetName) {
        this.targetName = Objects.requireNonNull(targetName);
    }

    @Override
    public String toString() {
        return "JumpTarget " + targetName;
    }
}
