package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.Return;

public interface ReturnTransformer extends BaseTransformer {

    public default void onReturnBefore(Return returnExpr) {

    }

    public default void onReturnAfter(Return returnExpr) {

    }

    public default Return transformBody(Return returnExpr) {
        return new Return(returnExpr.getPosition(), transform(returnExpr.getExpr()));
    }

    default Expression transform(Return returnExpr) {
        onReturnBefore(returnExpr);
        Return returnExprAfter = transformBody(returnExpr);
        onReturnAfter(returnExprAfter);
        return returnExprAfter;
    }

}