package org.karaffe.compiler.tree.transform.api;

import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface ApplyTransformer extends BaseTransformer, SimpleNameTransformer {

    public default void onApplyBefore(Apply apply) {

    }

    public default void onApplyAfter(Apply apply) {

    }

    public default Expression transform(Apply oldApply) {
        onApplyBefore(oldApply);
        Expression expression = transform(oldApply.getExpression());
        SimpleName methodName = oldApply.getMethodName();
        List<? extends Expression> args = oldApply.getArgs().stream().map(this::transform).collect(Collectors.toList());
        Apply after = new Apply(oldApply.getPosition(), expression, methodName, args);
        onApplyAfter(after);
        return after;
    }

}
