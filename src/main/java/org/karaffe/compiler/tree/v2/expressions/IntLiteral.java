package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.TreeVisitor;

public class IntLiteral extends Literal {
    private final int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    public IntLiteral(Position position, int value) {
        super(position);
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.INT_LITERAL;
    }
}
