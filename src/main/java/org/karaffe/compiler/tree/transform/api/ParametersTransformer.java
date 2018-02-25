package org.karaffe.compiler.tree.transform.api;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.v2.Parameter;

public interface ParametersTransformer extends ParameterTransformer {

    public default void onParametersBefore(List<? extends Parameter> parameters) {

    }

    public default void onParametersAfter(List<? extends Parameter> parameters) {

    }

    public default List<? extends Parameter> transform(List<? extends Parameter> oldParameter) {
        onParametersBefore(oldParameter);
        List<Parameter> parameters = new ArrayList<>();
        for (Parameter parameter : oldParameter) {
            parameters.add(transform(parameter));
        }
        onParametersAfter(parameters);
        return parameters;
    }
}
