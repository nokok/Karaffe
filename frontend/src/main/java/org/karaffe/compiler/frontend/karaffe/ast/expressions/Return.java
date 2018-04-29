package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import java.util.Objects;
import java.util.Optional;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractExpression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;

public class Return extends AbstractExpression {
    private final Expression expression;

    public Return(Expression expression) {
        this(Position.noPos(), expression);
    }

    public Return(Position position, Expression expression) {
        super(position);
        this.expression = Objects.requireNonNull(expression);
    }

    public Expression getExpr() {
        return this.expression;
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.RETURN;
    }

    @Override
    public Optional<ExpressionName> asExprName() {
        if (this.expression.getExpressionType().equals(ExpressionType.NAME)) {
            return Optional.of((ExpressionName) this.getExpr());
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("return %s", this.expression);
    }

}
