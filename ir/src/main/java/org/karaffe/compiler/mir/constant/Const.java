package org.karaffe.compiler.mir.constant;

import org.karaffe.compiler.mir.AbstractInstruction;

import java.util.Objects;

public class Const extends AbstractInstruction {
    private String value;
    private String kind;

    public Const(String value, String kind) {
        this.value = Objects.requireNonNull(value);
        this.kind = Objects.requireNonNull(kind);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Const aConst = (Const) o;
        return Objects.equals(value, aConst.value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = Objects.requireNonNull(kind);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "Const " + kind + " " + value;
    }
}
