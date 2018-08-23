package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.Instruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.IR;

import java.util.function.Function;

public interface InstructionTransformerBuilder {

    InstructionTransformerBuilder toEnd();

    <T extends Instruction> InstructionTransformerBuilder on(InstructionType type, Class<T> clazz, Function<T, IR> apply);

    InstructionTransformerBuilder onRemove(InstructionType type);

    InstructionTransformer build();
}
