package org.karaffe.compiler.base.ir;

import java.util.List;

public interface Instruction extends Element {
    Operand getOperand();

    List<Instruction> getArguments();
}
