package org.karaffe.compiler.base.ir;

import java.util.stream.Collectors;

public class DefaultIR extends AbstractElement {
    @Override
    public <R> R accept(IRVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.join("\n", this.getElements().stream().map(IR::toString).collect(Collectors.toList()));
    }
}
