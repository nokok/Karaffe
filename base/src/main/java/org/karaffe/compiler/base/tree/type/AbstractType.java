package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.term.Name;

import java.util.Objects;

public abstract class AbstractType extends AbstractTree implements Type {

    private Name typeName;
    private TypeKind typeKind;

    public AbstractType(Name typeName, TypeKind typeKind) {
        this(null, typeName, typeKind);
    }

    public AbstractType(Tree parent, Name typeName, TypeKind typeKind) {
        super(parent, TreeKind.TYPE);
        this.typeName = Objects.requireNonNull(typeName);
        this.typeKind = Objects.requireNonNull(typeKind);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        switch (this.typeKind) {
        case SIMPLE:
            return visitor.visitRefType((Type) this, p);
        case ARRAY:
            return visitor.visitArrayType((Type) this, p);
        case INT:
            return visitor.visitPrimitiveIntType((Type) this, p);
        case CHAR:
            return visitor.visitPrimitiveCharType((Type) this, p);
        case BYTE:
            return visitor.visitPrimitiveByteType((Type) this, p);
        case VOID:
            return visitor.visitVoidType((Type) this, p);
        case BOOLEAN:
            return visitor.visitPrimitiveBooleanType((Type) this, p);
        case LONG:
            return visitor.visitPrimitiveLongType((Type) this, p);
        case FLOAT:
            return visitor.visitPrimitiveFloatType((Type) this, p);
        case SHORT:
            return visitor.visitPrimitiveShortType((Type) this, p);
        case DOUBLE:
            return visitor.visitPrimitiveDoubleType((Type) this, p);
        case NO_TYPE:
            return visitor.visitNoType((Type) this, p);
        default:
            throw new IllegalStateException();
        }
    }

    @Override
    public Name getName() {
        return this.typeName;
    }

    public TypeKind getTypeKind() {
        return this.typeKind;
    }
}
