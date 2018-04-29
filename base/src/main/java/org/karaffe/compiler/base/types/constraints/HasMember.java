package org.karaffe.compiler.base.types.constraints;

import java.util.Objects;

public class HasMember implements TypeConstraint {

    private final TypeConstraint owner;
    private final String expectedMemberName;

    public HasMember(TypeConstraint owner, String expectedMemberName) {
        this.owner = Objects.requireNonNull(owner);
        this.expectedMemberName = Objects.requireNonNull(expectedMemberName);
    }

    public TypeConstraint getOwnerConstraint() {
        return this.owner;
    }

    public String getMemberName() {
        return this.expectedMemberName;
    }

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.HAS_MEMBER;
    }

    @Override
    public String toString() {
        return String.format("%s.%s", this.owner, this.expectedMemberName);
    }

}
