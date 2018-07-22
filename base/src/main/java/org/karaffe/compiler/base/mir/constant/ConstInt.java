package org.karaffe.compiler.base.mir.constant;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;

import java.util.Objects;

public class ConstInt extends AbstractInstruction {

    private String value;

    public ConstInt(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.CONSTINT;
    }
}
