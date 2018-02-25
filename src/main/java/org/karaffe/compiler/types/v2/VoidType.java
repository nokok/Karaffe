package org.karaffe.compiler.types.v2;

import java.util.List;

import org.karaffe.compiler.tree.v2.names.SimpleName;

public class VoidType implements TypeConstraint {

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.VOID;
    }

    @Override
    public boolean isApplicable(SimpleName methodName, List<? extends TypeConstraint> constraints) {
        return false;
    }

}
