package org.karaffe.compiler.tree.transform.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.Parameter;

public interface ParametersTransformer extends ParameterTransformer {

    public default void onParametersBefore(List<? extends Parameter> parameters) {

    }

    public default void onParametersAfter(List<? extends Parameter> parameters) {

    }

    public default List<? extends Parameter> transformBody(List<? extends Parameter> oldParameter) {
        return new ArrayList<>(oldParameter).stream().map(this::transform).collect(Collectors.toList());
    }

    public default List<? extends Parameter> transform(List<? extends Parameter> oldParameter) {
        onParametersBefore(oldParameter);
        List<? extends Parameter> parameters = transformBody(oldParameter);
        onParametersAfter(parameters);
        return parameters;
    }
}
