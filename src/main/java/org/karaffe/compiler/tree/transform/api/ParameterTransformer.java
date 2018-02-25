package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.Parameter;

public interface ParameterTransformer extends SimpleNameTransformer, TypeNameTransformer {
    public default void onParameterBefore(Parameter parameter) {

    }

    public default void onParameterAfter(Parameter parameter) {

    }

    public default Parameter transform(Parameter oldParameter) {
        onParameterBefore(oldParameter);
        Parameter after = new Parameter(transform(oldParameter.getName()), transform(oldParameter.getType()));
        onParameterAfter(after);
        return after;
    }
}
