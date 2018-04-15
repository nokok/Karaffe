package org.karaffe.compiler.frontend.karaffe.transformer.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.StaticApply;

public interface StaticApplyTransformer extends TypeNameTransformer, BaseTransformer {

    public default void onStaticApplyBefore(StaticApply staticApply) {

    }

    public default void onStaticApplyAfter(StaticApply staticApply) {

    }

    public default StaticApply transformBody(StaticApply staticApply) {
        return new StaticApply(
                transform(staticApply.getTypeName()),
                transform(staticApply.getMethodName()),
                staticApply.getArgs().stream().map(this::transform).collect(Collectors.toList()));
    }

    public default Expression transform(StaticApply staticApply) {
        onStaticApplyBefore(staticApply);
        StaticApply transformBody = transformBody(staticApply);
        onStaticApplyAfter(transformBody);
        return transformBody;
    }
}
