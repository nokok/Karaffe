package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.Return;

public interface ExpressionTransformer extends
        ApplyTransformer,
        BlockTransformer,
        ExpressionNameTransformer,
        IntLiteralTransformer,
        ReturnTransformer {

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
        default:
            throw new UnsupportedOperationException();
        }
    }

}
