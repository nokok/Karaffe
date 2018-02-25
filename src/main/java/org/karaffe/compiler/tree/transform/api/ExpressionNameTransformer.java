package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;

public interface ExpressionNameTransformer {

    public default void onExpressionName(ExpressionName expressionName) {

    }

    default Expression transform(ExpressionName expressionName) {
        onExpressionName(expressionName);
        return new ExpressionName(expressionName);
    }

}