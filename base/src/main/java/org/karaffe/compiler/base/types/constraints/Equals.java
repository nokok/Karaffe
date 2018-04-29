package org.karaffe.compiler.base.types.constraints;

import java.util.Objects;

public class Equals implements TypeConstraint {

    private final TypeConstraint left;
    private final TypeConstraint right;

    public Equals(TypeConstraint left, TypeConstraint right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    public TypeConstraint getLeftConstraint() {
        return this.left;
    }

    public TypeConstraint getRightConstraint() {
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
