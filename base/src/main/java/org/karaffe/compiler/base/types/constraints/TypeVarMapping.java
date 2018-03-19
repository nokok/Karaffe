package org.karaffe.compiler.base.types.constraints;

import java.util.Objects;

public class TypeVarMapping implements TypeConstraint {

    private final String name;

    public TypeVarMapping(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.TYPE_VAR;
    }

    @Override
    public String toString() {
        return "?" + this.name;
    }
}
