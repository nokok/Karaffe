package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public interface Type extends Tree {
    @Override
    default <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(Type.this, p);
    }

    <R, P> R accept(TypeVisitor<R, P> visitor, P p);
}

