package org.karaffe.compiler.ast.api;

import org.karaffe.compiler.ast.expressions.ExpressionType;

public interface Expression extends Statement, NameRef {
    @Override
    public default StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    public ExpressionType getExpressionType();

}
