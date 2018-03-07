package org.karaffe.compiler.tree.v2.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractExpression;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class Apply extends AbstractExpression {
    private final Expression expression;
    private final SimpleName methodName;
    private final List<? extends Expression> args;

    public Apply(SimpleName methodName, Expression... args) {
        this(new This(), methodName, args);
    }

    public Apply(Expression expression, SimpleName methodName, Expression... args) {
        this(expression, methodName, Arrays.asList(args));
    }

    public Apply(Expression expression, SimpleName methodName, List<? extends Expression> args) {
        this(Position.noPos(), expression, methodName, args);
    }

    public Apply(Position position, SimpleName methodName, Expression... args) {
        this(position, new This(), methodName, args);
    }

    public Apply(Position position, Expression expression, SimpleName methodName, Expression... args) {
        this(position, expression, methodName, Arrays.asList(args));
    }

    public Apply(Position position, Expression expression, SimpleName methodName, List<? extends Expression> args) {
        super(position);
        this.expression = expression;
        this.methodName = methodName;
        this.args = new ArrayList<>(args);
    }

    public Apply(Apply other) {
        this(other.getPosition(), other.getExpression(), other.getMethodName(), other.getArgs());
    }

    public Expression getExpression() {
        return this.expression;
    }

    public SimpleName getMethodName() {
        return this.methodName;
    }

    public List<? extends Expression> getArgs() {
        return new ArrayList<>(this.args);
    }

    @Override
    public String toString() {
        return String.format("%s.%s(%s)",
                this.expression,
                this.methodName,
                String.join(", ", this.args.stream().map(Expression::toString).collect(Collectors.toList())));
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.APPLY;
    }

    @Override
    public boolean isNormalizable() {
        boolean isExprNormalizable = !this.expression.getExpressionType().equals(ExpressionType.NAME);
        boolean isArgsNormalizable = this.args.stream().filter(expr -> !expr.getExpressionType().equals(ExpressionType.NAME)).findAny().isPresent();
        return isExprNormalizable || isArgsNormalizable;
    }

}
