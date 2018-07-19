package org.karaffe.compiler.base.mir.transformer;

import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;

import java.util.function.Function;

public interface InstructionTransformerBuilder {

    InstructionTransformerBuilder toEnd();

    <T extends Instruction> InstructionTransformerBuilder on(InstructionType type, Class<T> clazz, Function<T, Instructions> apply);

    InstructionTransformerBuilder onRemove(InstructionType type);

    InstructionTransformer build();
}
