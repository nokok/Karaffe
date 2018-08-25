package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;

import java.util.function.Function;

public interface InstructionTransformerBuilder {

    InstructionTransformerBuilder toEnd();

    <T extends DeprecatedInstruction> InstructionTransformerBuilder on(InstructionType type, Class<T> clazz, Function<T, DeprecatedInstructions> apply);

    InstructionTransformerBuilder onRemove(InstructionType type);

    InstructionTransformer build();
}
