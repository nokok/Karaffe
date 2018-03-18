package org.karaffe.compiler.tree.transform.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.expressions.Apply;

public interface ApplyTransformer extends BaseTransformer, SimpleNameTransformer {

    public default void onApplyBefore(Apply apply) {

    }

    public default void onApplyAfter(Apply apply) {

    }

    public default Apply transformBody(Apply oldApply) {
        return new Apply(
                oldApply.getPosition(),
                transform(oldApply.getExpression()),
                transform(oldApply.getMethodName()),
                oldApply.getArgs().stream().map(this::transform).collect(Collectors.toList()));
    }

    public default Expression transform(Apply oldApply) {
        onApplyBefore(oldApply);
        Apply after = transformBody(oldApply);
        onApplyAfter(after);
        return after;
    }

}
