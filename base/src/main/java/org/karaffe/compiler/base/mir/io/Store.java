package org.karaffe.compiler.base.mir.io;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class Store extends AbstractInstruction {
    private Label label;

    public Store(Label label) {
        this.label = Objects.requireNonNull(label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(label, store.label);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), label);
    }

    @Override
    public String toString() {
        return "Store " + label;
    }
}
