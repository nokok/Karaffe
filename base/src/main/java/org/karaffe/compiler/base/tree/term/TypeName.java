package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class TypeName extends AbstractTree {

    private String typeName;

    public TypeName(String typeName) {
        this.typeName = Objects.requireNonNull(typeName);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    @Override
    public String toString() {
        return typeName;
    }
}
