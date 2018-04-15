package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Return;

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