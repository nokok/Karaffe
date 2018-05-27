package org.karaffe.compiler.base.tree.type;

public interface TypeVisitor<R, P> {
    R visit(SimpleType simpleType, P p);

    R visit(PrimitiveType primitiveType, P p);

    R visit(ArrayType arrayType, P p);

}
