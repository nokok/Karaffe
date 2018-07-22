package org.karaffe.compiler.base.mir.transformer;

import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.util.InstructionList;

class InstructionTransformerImpl implements InstructionTransformer {

    private final TransformerConfig config;

    InstructionTransformerImpl(TransformerConfig config) {
        this.config = config;
    }

    @Override
    public Instructions transform(Instructions instructions) {
        Instructions dest = new InstructionList();
        dest.addAll(instructions);
        for (Instruction instruction : instructions) {
            if (config.getRemoveTypes().contains(instruction.getInstType())) {
                dest.remove(instruction);
            }
        }
        return dest;
    }
}
