package org.karaffe.compiler.types.v2;

import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class Reference implements TypeConstraint {

    private final ExpressionName expressionName;

    public Reference(ExpressionName expressionName) {
        this.expressionName = Objects.requireNonNull(expressionName);
    }

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.REFERENCE;
    }

    @Override
    public boolean isApplicable(SimpleName methodName, List<? extends TypeConstraint> constraints) {
        return false;
    }

}
