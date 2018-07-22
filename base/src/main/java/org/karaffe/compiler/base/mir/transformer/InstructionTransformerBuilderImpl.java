package org.karaffe.compiler.base.mir.transformer;

import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;

import java.util.function.Function;

class InstructionTransformerBuilderImpl implements InstructionTransformerBuilder {

    private TransformerConfig config = new TransformerConfig();

    @Override
    public InstructionTransformerBuilder toEnd() {
        return this;
    }

    @Override
    public <T extends Instruction> InstructionTransformerBuilder on(InstructionType type, Class<T> clazz, Function<T, Instructions> apply) {
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
