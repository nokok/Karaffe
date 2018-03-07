package org.karaffe.compiler.tree.v2.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractExpression;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;

public class StaticApply extends AbstractExpression {
    private final TypeName typeName;
    private final SimpleName methodName;
    private final List<Expression> args;

    public StaticApply(TypeName typeName, SimpleName methodName, List<Expression> args) {
        this.typeName = typeName;
        this.methodName = methodName;
        this.args = args;
    }

    public StaticApply(Position position, TypeName typeName, SimpleName methodName, List<Expression> args) {
        super(position);
        this.typeName = typeName;
        this.methodName = methodName;
        this.args = args;
    }

    public StaticApply(TypeName typeName, SimpleName methodName, Expression... args) {
        this.typeName = typeName;
        this.methodName = methodName;
        this.args = Arrays.asList(args);
    }

    public StaticApply(Position position, TypeName typeName, SimpleName methodName, Expression... args) {
        super(position);
        this.typeName = typeName;
        this.methodName = methodName;
        this.args = Arrays.asList(args);
    }

    public TypeName getTypeName() {
        return this.typeName;
    }

    public SimpleName getMethodName() {
        return this.methodName;
    }

    public List<? extends Expression> getArgs() {
        return new ArrayList<>(this.args);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.STATIC_APPLY;
    }

    @Override
    public String toString() {
        return String.format("%s.%s(%s)", this.typeName, this.methodName, String.join(",", this.args.stream().map(Expression::toString).collect(Collectors.toList())));
    }
}
