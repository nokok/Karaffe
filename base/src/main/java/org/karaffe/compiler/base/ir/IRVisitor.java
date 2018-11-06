package org.karaffe.compiler.base.ir;

public interface IRVisitor<R> {

    R visit(Module module);

    R visit(DefaultIR ir);

    R visit(Class clazz);
}
