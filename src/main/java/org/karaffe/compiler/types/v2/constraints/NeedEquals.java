package org.karaffe.compiler.types.v2.constraints;

import java.util.Objects;

import org.karaffe.compiler.tree.v2.api.Tree;

public class NeedEquals implements TypeConstraint {

    private final Tree left;
    private final Tree right;

    public NeedEquals(Tree left, Tree right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    public Tree getLeftTree() {
        return this.left;
    }

    public Tree getRightTree() {
        return this.right;
    }

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.NEED_EQUALS;
    }

    @Override
    public String toString() {
        return String.format("%s == %s", this.left, this.right);
    }

}
