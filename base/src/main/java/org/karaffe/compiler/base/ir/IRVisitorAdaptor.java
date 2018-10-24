package org.karaffe.compiler.base.ir;

public class IRVisitorAdaptor<R> implements IRVisitor<R> {
    @Override
    public R visit(Module module) {
        return null;
    }
}
