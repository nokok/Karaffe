package org.karaffe.compiler.base.mir.transformer;

import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.util.Label;

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

    public Instructions transform(Instructions instructions);
}
