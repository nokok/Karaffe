package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.term.Name;

import java.util.Objects;

public abstract class AbstractType extends AbstractTree implements Type {

    private final Tree typeName;
    private final TypeKind typeKind;

    public AbstractType(Name typeName, TypeKind typeKind) {
        this(null, typeName, typeKind);
    }

    public AbstractType(Tree parent, Tree typeName, TypeKind typeKind) {
        super(parent, TreeKind.TYPE);
        this.typeName = Objects.requireNonNull(typeName);
        this.typeKind = Objects.requireNonNull(typeKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        switch (this.typeKind) {
        case SIMPLE:
            return visitor.visitRefType(this, p);
        case ARRAY:
            return visitor.visitArrayType(this, p);
        case INT:
            return visitor.visitPrimitiveIntType(this, p);
        case CHAR:
            return visitor.visitPrimitiveCharType(this, p);
        case BYTE:
            return visitor.visitPrimitiveByteType(this, p);
        case VOID:
            return visitor.visitVoidType(this, p);
        case BOOLEAN:
            return visitor.visitPrimitiveBooleanType(this, p);
        case LONG:
            return visitor.visitPrimitiveLongType(this, p);
        case FLOAT:
            return visitor.visitPrimitiveFloatType(this, p);
        case SHORT:
            return visitor.visitPrimitiveShortType(this, p);
        case DOUBLE:
            return visitor.visitPrimitiveDoubleType(this, p);
        case NO_TYPE:
            return visitor.visitNoType(this, p);
        default:
            throw new IllegalStateException();
        }
    }

    @Override
    public Tree getName() {
        return this.typeName;
    }

    public TypeKind getTypeKind() {
        return this.typeKind;
    }
}
