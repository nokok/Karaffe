package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class InternalName extends AbstractTree {

    private String name;

    public InternalName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
