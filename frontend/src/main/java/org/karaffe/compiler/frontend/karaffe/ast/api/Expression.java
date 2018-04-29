package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionType;

public interface Expression extends Statement, NameRef {
    @Override
    default StatementType getStatementType() {
        return StatementType.EXPRESSION;
    }

    ExpressionType getExpressionType();

}
