package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.Tree;

import java.util.Objects;

public class ArrayType extends AbstractType {

    private Tree elementType;

    public ArrayType(Tree elementType) {
        this(null, elementType);
    }

    public ArrayType(Tree parent, Tree elementType) {
        super(parent, elementType.getName(), TypeKind.ARRAY);
        this.elementType = Objects.requireNonNull(elementType);
    }

    public Tree getElementType() {
        return elementType;
    }

    public void setElementType(Tree type) {
        this.elementType = Objects.requireNonNull(type);
    }
}
