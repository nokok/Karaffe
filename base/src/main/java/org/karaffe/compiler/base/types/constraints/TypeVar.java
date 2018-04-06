package org.karaffe.compiler.base.types.constraints;

import java.util.Objects;

public class TypeVar implements TypeConstraint {

    private final String name;

    public TypeVar(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.TYPE_VAR;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof TypeVar) {
            TypeVar other = (TypeVar) obj;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return "?" + this.name;
    }
}
