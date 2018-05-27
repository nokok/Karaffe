package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Terms;

public class PrimitiveType extends AbstractType {

    PrimitiveType(TypeKind typeKind) {
        this(null, typeKind);
    }

    PrimitiveType(Tree parent, TypeKind typeKind) {
        super(parent, Terms.typeName(typeKind.name()), typeKind);
    }

    @Override
    public <R, P> R accept(TypeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
