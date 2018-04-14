package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.expressions.Apply;
import org.karaffe.compiler.ast.expressions.Block;
import org.karaffe.compiler.ast.expressions.ExpressionName;
import org.karaffe.compiler.ast.expressions.IntLiteral;
import org.karaffe.compiler.ast.expressions.NewInstance;
import org.karaffe.compiler.ast.expressions.Return;
import org.karaffe.compiler.ast.expressions.StaticApply;

public interface ExpressionTransformer extends
        ApplyTransformer,
        BlockTransformer,
        ExpressionNameTransformer,
        IntLiteralTransformer,
        ReturnTransformer,
        StaticApplyTransformer,
        NewInstanceTransformer {

    public default void onExpressionBefore(Expression expression) {

    }

    @Override
    public default Expression transform(Expression expression) {
        onExpressionBefore(expression);
        switch (expression.getExpressionType()) {
        case APPLY:
            return transform((Apply) expression);
        case BLOCK:
            return transform((Block) expression);
        case INT_LITERAL:
            return transform((IntLiteral) expression);
        case NAME:
            return transform((ExpressionName) expression);
        case RETURN:
            return transform((Return) expression);
        case STATIC_APPLY:
            return transform((StaticApply) expression);
        case NEW_INSTANCE:
            return transform((NewInstance) expression);
        default:
            throw new UnsupportedOperationException();
        }
    }

}
