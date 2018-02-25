package org.karaffe.compiler.types.v2;

import java.util.List;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.names.SimpleName;

import karaffe.core.Int;

public interface TypeConstraints {
    public static TypeConstraint infer(Expression expression) {
        switch (expression.getExpressionType()) {
        case INT_LITERAL:
            return TypeConstraints.of(expression, Int.class);
        default:
            return TypeConstraints.noHint();
        }
    }

    public static TypeConstraint infer(Expression expression, SimpleName methodName, List<? extends Expression> args) {
        TypeConstraint exprConstraint = TypeConstraints.infer(expression);
        return null;
    }

    public static TypeConstraint of(Expression expression, Class<? extends Object> clazz) {
        return new Successful(expression, clazz);
    }

    public static TypeConstraint of(SimpleName typeName) {
        return null;
    }

    public static TypeConstraint noHint() {
        return new NoHint();
    }

    public static TypeConstraint voidType() {
        return new VoidType();
    }

    public static TypeConstraint reference(ExpressionName exprName) {
        return new Reference(exprName);
    }

    public static TypeConstraint infer(Statement statement) {
        return null;
    }
}
