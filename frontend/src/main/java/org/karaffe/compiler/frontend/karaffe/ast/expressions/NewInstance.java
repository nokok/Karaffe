package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractExpression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.NameRef;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewInstance extends AbstractExpression {

    private final TypeName typeName;
    private final SimpleName methodName = new SimpleName("<init>");
    private final List<Expression> args;

    public NewInstance(TypeName typeName) {
        this(Position.noPos(), typeName, new ArrayList<>(0));
    }

    public NewInstance(TypeName typeName, List<? extends Expression> args) {
        this(Position.noPos(), typeName, args);
    }

    public NewInstance(Position position, TypeName typeName, List<? extends Expression> args) {
        super(position);
        this.typeName = typeName;
        this.args = new ArrayList<>(args);
    }

    @Override
    public boolean isNormalizable() {
        return this.args.stream().anyMatch(arg -> !arg.getExpressionType().equals(ExpressionType.NAME));
    }

    public TypeName getTypeName() {
        return this.typeName;
    }

    public List<? extends Expression> getArgs() {
        return new ArrayList<>(this.args);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.NEW_INSTANCE;
    }

    @Override
    public String toString() {
        return String.format("%s.%s(%s)", this.typeName, this.methodName, String.join(",", this.args.stream().map(Expression::toString).collect(Collectors.toList())));
    }

}
