package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.Tree;

import java.util.Objects;

public class ArrayType extends AbstractType {

    private Type elementType;

    public ArrayType(Type elementType) {
        this(null, elementType);
    }

    public ArrayType(Tree parent, Type elementType) {
        super(parent, elementType.getName(), TypeKind.ARRAY);
        this.elementType = Objects.requireNonNull(elementType);
    }

    @Override
    public <R, P> R accept(TypeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
