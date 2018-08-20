package org.karaffe.compiler.base.mir.instructions.block;

import org.karaffe.compiler.base.mir.instructions.util.Label;

public interface ScopeInstruction {
    Label getScopeName();
}
