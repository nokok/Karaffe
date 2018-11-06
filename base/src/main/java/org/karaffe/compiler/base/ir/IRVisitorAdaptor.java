package org.karaffe.compiler.base.ir;

public class IRVisitorAdaptor<R> implements IRVisitor<R> {
    @Override
    public R visit(Module module) {
        return null;
    }

    @Override
    public R visit(DefaultIR ir) {
        return null;
    }

    @Override
    public R visit(Class clazz) {
        return null;
    }
}
