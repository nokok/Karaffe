package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.term.Terms;

class SimpleType extends AbstractType {

    static final SimpleType NO_TYPE = new SimpleType(Terms.specialName("__EMPTY__"), TypeKind.NO_TYPE);

    SimpleType(Name typeName, TypeKind typeKind) {
        this(null, typeName, typeKind);
    }

    SimpleType(Tree parent, Name typeName, TypeKind typeKind) {
        super(parent, typeName, typeKind);
    }
}
