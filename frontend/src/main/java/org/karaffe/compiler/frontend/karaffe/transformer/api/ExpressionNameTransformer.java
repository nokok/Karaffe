package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.expressions.ExpressionName;

public interface ExpressionNameTransformer {

    public default void onExpressionNameBefore(ExpressionName expressionName) {

    }

    public default void onExpressionNameAfter(ExpressionName expressionName) {

    }

    default Expression transform(ExpressionName expressionName) {
        onExpressionNameBefore(expressionName);
        ExpressionName after = transformBody(expressionName);
        onExpressionNameAfter(expressionName);
        return after;
    }

    public default ExpressionName transformBody(ExpressionName expressionName) {
        return new ExpressionName(expressionName);
    }

}