package org.karaffe.compiler.transformer.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.expressions.NewInstance;

public interface NewInstanceTransformer extends TypeNameTransformer, BaseTransformer {
    public default void onNewInstanceBefore(NewInstance newInstance) {

    }

    public default void onNewInstanceAfter(NewInstance newInstance) {

    }

    public default NewInstance transformBody(NewInstance newInstance) {
        return new NewInstance(
                newInstance.getPosition(),
                transform(newInstance.getTypeName()),
                newInstance.getArgs().stream().map(this::transform).collect(Collectors.toList()));
    }

    public default NewInstance transform(NewInstance newInstance) {
        onNewInstanceBefore(newInstance);
        NewInstance after = transformBody(newInstance);
        onNewInstanceAfter(after);
        return after;
    }
}
