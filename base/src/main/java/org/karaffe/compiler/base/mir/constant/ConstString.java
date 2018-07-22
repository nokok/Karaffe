package org.karaffe.compiler.base.mir.constant;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;

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
}
