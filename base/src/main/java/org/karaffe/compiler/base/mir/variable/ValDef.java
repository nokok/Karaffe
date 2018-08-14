package org.karaffe.compiler.base.mir.variable;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.attr.ParameterAttribute;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class ValDef extends AbstractInstruction {
    private Label valName;
    private String typeName;

    public ValDef(Label valName, String typeName) {
        this.valName = Objects.requireNonNull(valName);
        this.typeName = Objects.requireNonNull(typeName);
    }

    public Label getValName() {
        return valName;
    }

    public String getTypeName() {
        return typeName;
    }

    public boolean isParameter() {
        return this.getAttributes().stream().anyMatch(i -> i instanceof ParameterAttribute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValDef valDef = (ValDef) o;
        return Objects.equals(valName, valDef.valName) &&
                Objects.equals(typeName, valDef.typeName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), valName, typeName);
    }

    @Override
    public String toString() {
        return "ValDef " + valName + " " + typeName;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.VALDEF;
    }
}
