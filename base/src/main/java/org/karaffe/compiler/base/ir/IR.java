package org.karaffe.compiler.base.ir;

public interface IR {
    static IR newIR() {
        return null;
    }

    <R> R accept(IRVisitor<R> visitor);
}
