package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractExpression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class StaticApply extends AbstractExpression {
    private final TypeName typeName;
    private final SimpleName methodName;
    private final List<Expression> args;

    public StaticApply(TypeName typeName, SimpleName methodName, List<? extends Expression> args) {
        this(Position.noPos(), typeName, methodName, args);
    }

    public StaticApply(Position position, TypeName typeName, SimpleName methodName, List<? extends Expression> args) {
        super(position);
        this.typeName = typeName;
        this.methodName = methodName;
        this.args = new ArrayList<>(args);
    }

    public StaticApply(TypeName typeName, SimpleName methodName, Expression... args) {
        this(Position.noPos(), typeName, methodName, args);
    }

    public StaticApply(Position position, TypeName typeName, SimpleName methodName, Expression... args) {
        super(position);
        this.typeName = typeName;
        this.methodName = methodName;
        this.args = Arrays.asList(args);
    }

    public StaticApply(Position position, FullyQualifiedTypeName typeName, SimpleName methodName, Expression... args) {
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
        return String.format("%s::%s(%s)", this.typeName, this.methodName, String.join(",", this.args.stream().map(Expression::toString).collect(Collectors.toList())));
    }
}
