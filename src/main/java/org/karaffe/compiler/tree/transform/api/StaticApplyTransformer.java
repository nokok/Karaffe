package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.StaticApply;

public interface StaticApplyTransformer {

    public default void onStaticApplyBefore(StaticApply staticApply) {

    }

    public default void onStaticApplyAfter(StaticApply staticApply) {

    }

    public default StaticApply transformBody(StaticApply staticApply) {
        return staticApply; // TODO transform
    }

    public default Expression transform(StaticApply staticApply) {
        onStaticApplyBefore(staticApply);
        StaticApply transformBody = transformBody(staticApply);
        onStaticApplyAfter(transformBody);
        return transformBody;
    }
}
