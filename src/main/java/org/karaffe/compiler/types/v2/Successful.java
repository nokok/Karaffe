package org.karaffe.compiler.types.v2;

import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class Successful implements TypeConstraint {
    private final Expression expression;
    private final Class<?> clazz;

    public Successful(Expression expression, Class<?> clazz) {
        this.expression = Objects.requireNonNull(expression);
        this.clazz = Objects.requireNonNull(clazz);
    }

    @Override
    public String toString() {
        return String.format("%s = %s", this.expression, this.clazz.getCanonicalName());
    }

    @Override
    public ConstraintType getConstraintType() {
        return ConstraintType.SUCCESSFUL;
    }

    @Override
    public boolean isApplicable(SimpleName methodName, List<? extends TypeConstraint> constraints) {
        return false;
    }
}
