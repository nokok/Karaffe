package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.Parameter;

public interface ParameterTransformer extends TypeNameTransformer {
    public default void onParameterBefore(Parameter parameter) {

    }

    public default void onParameterAfter(Parameter parameter) {

    }

    public default Parameter transform(Parameter oldParameter) {
        onParameterBefore(oldParameter);
        Parameter after = transformBody(oldParameter);
        onParameterAfter(after);
        return after;
    }

    public default Parameter transformBody(Parameter parameter) {
        return new Parameter(parameter.getPosition(),
                transform(parameter.getName()),
                transform(parameter.getType()));
    }

}
