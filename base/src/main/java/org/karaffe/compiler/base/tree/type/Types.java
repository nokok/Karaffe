package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.term.Name;

public interface Types {
    static Type noType() {
        return SimpleType.NO_TYPE;
    }

    static Type simple(Name typeName) {
        return new SimpleType(typeName, TypeKind.SIMPLE);
    }

    static Type jInt() {
        return new PrimitiveType(TypeKind.INT);
    }

    static Type array(Type elementType) {
        return new ArrayType(elementType);
    }
}
