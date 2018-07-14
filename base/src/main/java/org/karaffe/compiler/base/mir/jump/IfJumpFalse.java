package org.karaffe.compiler.base.mir.jump;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.util.Label;

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
