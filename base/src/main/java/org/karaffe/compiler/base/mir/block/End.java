package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class End extends AbstractInstruction implements ScopeInstruction {
    private Label label;

    public End(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        End end = (End) o;
        return Objects.equals(label, end.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return "End " + label;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.END;
    }

    @Override
    public Label getScopeName() {
        return this.label;
    }
}
