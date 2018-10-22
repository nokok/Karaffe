package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class VarName extends AbstractTree {

    private String varName;

    public VarName(String varName) {
        this.varName = Objects.requireNonNull(varName);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    @Override
    public String toString() {
        return varName;
    }
}
