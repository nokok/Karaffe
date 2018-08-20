package org.karaffe.compiler.base.mir.instructions;

import org.karaffe.compiler.base.mir.instructions.util.Label;

public interface LabeledInstruction {
    Label getLabel();
}
