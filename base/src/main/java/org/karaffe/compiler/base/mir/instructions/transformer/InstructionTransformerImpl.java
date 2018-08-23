package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.Instruction;
import org.karaffe.compiler.base.mir.instructions.IR;
import org.karaffe.compiler.base.mir.instructions.util.InstructionList;

class InstructionTransformerImpl implements InstructionTransformer {

    private final TransformerConfig config;

    InstructionTransformerImpl(TransformerConfig config) {
        this.config = config;
    }

    @Override
    public IR transform(IR instructions) {
        IR dest = new InstructionList();
        dest.addAll(instructions);
        for (Instruction instruction : instructions) {
            if (config.getRemoveTypes().contains(instruction.getInstType())) {
                dest.remove(instruction);
            }
        }
        return dest;
    }
}
