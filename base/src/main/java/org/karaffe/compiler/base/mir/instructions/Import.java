package org.karaffe.compiler.base.mir.instructions;

import org.karaffe.compiler.base.tree.term.Path;

public class Import extends AbstractInstruction {
    private final Path name;

    public Import(Path name) {
        this.name = name;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.IMPORT;
    }

    @Override
    public String toString() {
        return "Import " + name.asFullName();
    }
}
