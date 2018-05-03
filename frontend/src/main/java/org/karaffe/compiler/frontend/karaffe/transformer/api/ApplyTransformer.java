package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;

import java.util.stream.Collectors;

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
