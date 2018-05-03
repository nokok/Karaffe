package org.karaffe.compiler.backend.jvm.resolvers.types;

import org.karaffe.compiler.backend.jvm.resolvers.types.state.JavaTypeResolved;
import org.karaffe.compiler.base.types.state.InferState;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface JavaInferStates extends InferState {
    public static InferState of(Class<? extends Object> clazz) {
        return new JavaTypeResolved(clazz);
    }

    public static InferState of(Class<?>... clazz) {
        return new JavaTypeResolved(Arrays.asList(clazz));
    }

    public static InferState of(List<String> allCompatibleClasses) {
        return new JavaTypeResolved(allCompatibleClasses.stream().<Optional<Class<?>>>map(clazzName -> {
            try {
                return Optional.ofNullable(Class.forName(clazzName));
            } catch (ClassNotFoundException e) {
                return Optional.empty();
            }
        }).map(Optional::get).collect(Collectors.toList()));
    }

}
