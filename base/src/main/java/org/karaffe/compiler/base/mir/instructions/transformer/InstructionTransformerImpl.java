package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;
import org.karaffe.compiler.base.mir.instructions.util.InstructionList;

class InstructionTransformerImpl implements InstructionTransformer {

    private final TransformerConfig config;

    InstructionTransformerImpl(TransformerConfig config) {
        this.config = config;
    }

    @Override
    public DeprecatedInstructions transform(DeprecatedInstructions instructions) {
        DeprecatedInstructions dest = new InstructionList();
        dest.addAll(instructions);
        for (DeprecatedInstruction instruction : instructions) {
            if (config.getRemoveTypes().contains(instruction.getInstType())) {
                dest.remove(instruction);
            }
        }
        return dest;
    }
}
