package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.NewInstance;
import org.karaffe.compiler.tree.v2.expressions.Return;
import org.karaffe.compiler.tree.v2.expressions.StaticApply;

public interface ExpressionTransformer extends
        ApplyTransformer,
        BlockTransformer,
        ExpressionNameTransformer,
        IntLiteralTransformer,
        ReturnTransformer,
        StaticApplyTransformer,
        NewInstanceTransformer {

    @Override
    public default Expression transform(Expression expression) {
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
