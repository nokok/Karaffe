package org.karaffe.compiler.mir.util;

import org.karaffe.compiler.mir.Instruction;
import org.karaffe.compiler.mir.Instructions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InstructionList extends ArrayList<Instruction> implements Instructions {

    @Override
    public String toString() {
        return String.join("\n", this.stream().map(i -> String.format("[%15s] %s", i.getPosition().toStringNoSource(), i)).collect(Collectors.toList()));
    }
}
