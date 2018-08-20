package org.karaffe.compiler.base.mir.instructions;

import java.util.Objects;

public class Cast extends AbstractInstruction {
    private String castType;

    public Cast(String castType) {
        this.castType = Objects.requireNonNull(castType);
    }

    @Override
    public String toString() {
        return "Cast " + this.castType;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.CAST;
    }
}
