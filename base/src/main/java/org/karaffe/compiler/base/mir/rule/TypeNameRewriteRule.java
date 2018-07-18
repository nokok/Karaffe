package org.karaffe.compiler.base.mir.rule;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;

import java.util.Objects;

public class TypeNameRewriteRule extends AbstractInstruction {

    private String beforeName;
    private String afterName;

    public TypeNameRewriteRule(String before, String after) {
        this.beforeName = Objects.requireNonNull(before);
        this.afterName = Objects.requireNonNull(after);
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.NAMERULE;
    }

    @Override
    public String toString() {
        return "TypeNameRewriteRule " + beforeName + " -> " + afterName;
    }
}
