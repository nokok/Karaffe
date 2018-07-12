package org.karaffe.compiler.mir.util;

import org.karaffe.compiler.mir.Instruction;
import org.karaffe.compiler.mir.Instructions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InstructionList extends ArrayList<Instruction> implements Instructions {

    @Override
    public String toString() {
        return String.join("\n", this.stream().map(i -> i + " " + i.getPosition()).collect(Collectors.toList()));
    }
}
