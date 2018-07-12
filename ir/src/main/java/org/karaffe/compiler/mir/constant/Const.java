package org.karaffe.compiler.mir.constant;

import org.karaffe.compiler.mir.AbstractInstruction;

import java.util.Objects;

public class Const extends AbstractInstruction {
    private String value;

    public Const(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Const aConst = (Const) o;
        return Objects.equals(value, aConst.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "Const " + value;
    }
}
