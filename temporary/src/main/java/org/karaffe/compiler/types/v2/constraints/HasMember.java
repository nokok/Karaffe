package org.karaffe.compiler.types.v2.constraints;

import java.util.Objects;

import org.karaffe.compiler.ast.api.Tree;

public class HasMember implements TypeConstraint {

    private final Tree owner;
    private final String expectedMemberName;

    public HasMember(Tree owner, String expectedMemberName) {
        this.owner = Objects.requireNonNull(owner);
        this.expectedMemberName = Objects.requireNonNull(expectedMemberName);
    }

    public Tree getOwner() {
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
