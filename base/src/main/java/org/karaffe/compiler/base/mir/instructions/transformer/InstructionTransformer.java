package org.karaffe.compiler.base.mir.instructions.transformer;

import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.IR;
import org.karaffe.compiler.base.mir.instructions.util.Label;

public interface InstructionTransformer {

    static InstructionTransformerBuilder begin(InstructionType type, Label label) {
        return begin(type, label.toString());
    }

    static InstructionTransformerBuilder begin(InstructionType type, String label) {
        return null;
    }

    static InstructionTransformerBuilder create() {
        return new InstructionTransformerBuilderImpl();
    }

    public IR transform(IR instructions);
}
