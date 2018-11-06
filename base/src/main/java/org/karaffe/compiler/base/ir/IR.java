package org.karaffe.compiler.base.ir;

public interface IR {
    static IR newIR() {
        return new DefaultIR();
    }

    <R> R accept(IRVisitor<R> visitor);
}
