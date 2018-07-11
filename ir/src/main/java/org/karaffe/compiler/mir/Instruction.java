package org.karaffe.compiler.mir;

public interface Instruction {
    InstructionType getInstType();

    void setInstType(InstructionType type);
}
