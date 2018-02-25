package org.karaffe.compiler.types.v2;

import java.util.List;

import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface TypeConstraint {
    public ConstraintType getConstraintType();

    public boolean isApplicable(SimpleName methodName, List<? extends TypeConstraint> constraints);
}
