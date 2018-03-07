package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.tree.v2.expressions.ExpressionType;

public interface Expression extends Statement, NameRef {
    @Override
    public default StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    public ExpressionType getExpressionType();

}
