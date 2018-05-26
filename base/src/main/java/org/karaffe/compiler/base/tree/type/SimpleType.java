package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.term.Terms;

class SimpleType implements Type {

    static final SimpleType NO_TYPE = new SimpleType("__NOTYPE__");

    private Name typeName;

    SimpleType(String typeName) {
        this(Terms.typeName(typeName));
    }

    SimpleType(Name typeName) {
        this.typeName = typeName;
    }

    @Override
    public <R, P> R accept(TypeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
