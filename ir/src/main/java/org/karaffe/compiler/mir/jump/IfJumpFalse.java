package org.karaffe.compiler.mir.jump;

import org.karaffe.compiler.mir.AbstractInstruction;
import org.karaffe.compiler.mir.util.Label;

import java.util.Objects;

public class IfJumpFalse extends AbstractInstruction {
    private Label jumpTarget;

    public IfJumpFalse(Label jumpTarget) {
        this.jumpTarget = Objects.requireNonNull(jumpTarget);
    }

    @Override
    public String toString() {
        return "IfJumpFalse " + jumpTarget;
    }
}
