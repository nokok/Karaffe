package org.karaffe.compiler.base.tree.type;

public class PrimitiveArrayType implements Type {
    @Override
    public <R, P> R accept(TypeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}