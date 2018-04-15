package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionType;

public interface Expression extends Statement, NameRef {
    @Override
    public default StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    public ExpressionType getExpressionType();

}
