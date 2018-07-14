package org.karaffe.compiler.base.mir.jump;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class IfJumpTrue extends AbstractInstruction {
    private Label jumpTarget;

    public IfJumpTrue(Label jumpTarget) {
        this.jumpTarget = Objects.requireNonNull(jumpTarget);
    }

    @Override
    public String toString() {
        return "IfJumpTrue " + jumpTarget;
    }
}
