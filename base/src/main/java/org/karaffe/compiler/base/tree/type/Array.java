package org.karaffe.compiler.base.tree.type;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class Array extends AbstractTree {

    private Tree elementType;

    public Array(Tree elementType) {
        this.elementType = Objects.requireNonNull(elementType);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
