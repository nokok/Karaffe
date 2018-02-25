package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.Return;

public interface ReturnTransformer extends BaseTransformer {

    public default void onReturnBefore(Return returnExpr) {

    }

    public default void onReturnAfter(Return returnExpr) {

    }

    default Expression transform(Return returnExpr) {
        onReturnBefore(returnExpr);
        Return returnExprAfter = new Return(transform(returnExpr.getExpr()));
        onReturnAfter(returnExprAfter);
        return returnExprAfter;
    }

}