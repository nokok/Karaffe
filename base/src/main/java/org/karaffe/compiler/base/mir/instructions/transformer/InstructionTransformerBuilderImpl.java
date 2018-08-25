package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;

import java.util.function.Function;

class InstructionTransformerBuilderImpl implements InstructionTransformerBuilder {

    private TransformerConfig config = new TransformerConfig();

    @Override
    public InstructionTransformerBuilder toEnd() {
        return this;
    }

    @Override
    public <T extends DeprecatedInstruction> InstructionTransformerBuilder on(InstructionType type, Class<T> clazz, Function<T, DeprecatedInstructions> apply) {
        return this;
    }

    @Override
    public InstructionTransformerBuilder onRemove(InstructionType type) {
        config.addRemoveInstructionRule(type);
        return this;
    }

    @Override
    public InstructionTransformer build() {
        return new InstructionTransformerImpl(config);
    }
}
