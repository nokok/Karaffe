package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.names.SimpleName;

public class ExpressionName extends SimpleName implements Expression {

    public ExpressionName(String name) {
        super(name);
    }

    public ExpressionName(SimpleName name) {
        super(name);
    }

    public ExpressionName(Position position, String name) {
        super(position, name);
    }

    public ExpressionName(Position position, SimpleName name) {
        super(position, name);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.NAME;
    }

}
