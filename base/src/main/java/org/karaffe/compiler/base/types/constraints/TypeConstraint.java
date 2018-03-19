package org.karaffe.compiler.base.types.constraints;

public interface TypeConstraint {
    static TypeConstraint needEquals(TypeConstraint left, TypeConstraint right) {
        return new Equals(left, right);
    }

    static TypeConstraint tyVar(String name) {
        return new TypeVarMapping(name);
    }

    public ConstraintType getConstraintType();
}
