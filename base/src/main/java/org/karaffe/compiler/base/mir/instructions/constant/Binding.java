package org.karaffe.compiler.base.mir.instructions.constant;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class Binding extends AbstractInstruction {
    private Label name;
    private String typeName;

    public Binding(Label name, String typeName) {
        this.name = Objects.requireNonNull(name);
        this.typeName = Objects.requireNonNull(typeName);
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Binding " + name + " " + typeName;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.BINDING;
    }
}
