package org.karaffe.compiler.base.mir.invoke;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;

import java.util.Objects;

public class Invoke extends AbstractInstruction {
    private String name;

    public Invoke(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String toString() {
        return "Invoke " + name;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.INVOKE;
    }
}
