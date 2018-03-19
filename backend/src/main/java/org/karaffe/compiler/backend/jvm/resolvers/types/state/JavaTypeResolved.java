package org.karaffe.compiler.backend.jvm.resolvers.types.state;

import org.karaffe.compiler.base.types.state.InferState;
import org.karaffe.compiler.base.types.state.InferStateType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaTypeResolved implements InferState {
    private final List<Class<?>> clazz;

    public JavaTypeResolved(Class<?> clazz) {
        this(Arrays.asList(clazz));
    }

    public JavaTypeResolved(List<? extends Class<?>> clazz) {
        this.clazz = new ArrayList<>(clazz);
        if (this.clazz.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public Class<?> getSuitableType() {
        return this.clazz.get(0);
    }

    @Override
    public String toString() {
        return String.format("%s", String.join(" | ", this.clazz.stream().map(Class::getCanonicalName).collect(Collectors.toList())));
    }

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.RESOLVED;
    }

}
