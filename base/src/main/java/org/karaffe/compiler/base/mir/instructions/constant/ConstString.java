package org.karaffe.compiler.base.mir.instructions.constant;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;

import java.util.Objects;

public class ConstString extends AbstractInstruction {
    private String value;

    public ConstString(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.CONSTSTRING;
    }

    @Override
    public String toString() {
        return "Const STRING " + value;
    }
}
