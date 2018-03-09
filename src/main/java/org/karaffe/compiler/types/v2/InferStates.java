package org.karaffe.compiler.types.v2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.types.v2.states.Error;
import org.karaffe.compiler.types.v2.states.InferState;
import org.karaffe.compiler.types.v2.states.NoHint;
import org.karaffe.compiler.types.v2.states.Resolved;
import org.karaffe.compiler.types.v2.states.VoidType;

public interface InferStates {
    public static InferState of(Class<? extends Object> clazz) {
        return new Resolved(clazz);
    }

    public static InferState of(Class<?>... clazz) {
        return new Resolved(Arrays.asList(clazz));
    }

    public static InferState noHint() {
        return new NoHint();
    }

    public static InferState voidType() {
        return new VoidType();
    }

    public static InferState fail() {
        return new Error();
    }

    public static InferState of(List<String> allCompatibleClasses) {
        return new Resolved(allCompatibleClasses.stream().<Optional<Class<?>>>map(clazzName -> {
            try {
                return Optional.ofNullable(Class.forName(clazzName));
            } catch (ClassNotFoundException e) {
                return Optional.empty();
            }
        }).map(Optional::get).collect(Collectors.toList()));
    }

}
