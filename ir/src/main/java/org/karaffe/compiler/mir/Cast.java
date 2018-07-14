package org.karaffe.compiler.mir;

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
}
