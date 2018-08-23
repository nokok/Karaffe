package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.Instruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.IR;

import java.util.function.Function;

class InstructionTransformerBuilderImpl implements InstructionTransformerBuilder {

    private TransformerConfig config = new TransformerConfig();

    @Override
    public InstructionTransformerBuilder toEnd() {
        return this;
    }

    @Override
    public <T extends Instruction> InstructionTransformerBuilder on(InstructionType type, Class<T> clazz, Function<T, IR> apply) {
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
