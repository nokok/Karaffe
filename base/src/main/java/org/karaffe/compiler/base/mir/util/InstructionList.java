package org.karaffe.compiler.base.mir.util;

import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.Instructions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InstructionList extends ArrayList<Instruction> implements Instructions {

    @Override
    public String toString() {
        return String.join("\n", this
                .stream()
                .map(i ->
                        String.format("[%15s]%s %s",
                                i.getPosition().toStringNoSource(),
                                i.hasAttribute() ? " " + i.getAttributes() : "",
                                i))
                .collect(Collectors.toList()));
    }
}
