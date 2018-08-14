package org.karaffe.compiler.base.mir.instance;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class Store extends AbstractInstruction {
    private Label storeName;

    public Store(Label storeName) {
        this.storeName = Objects.requireNonNull(storeName);
    }

    public Label getStoreName() {
        return storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(storeName, store.storeName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), storeName);
    }

    @Override
    public String toString() {
        return "Store " + storeName;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.STORE;
    }
}
