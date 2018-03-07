package org.karaffe.compiler.types.v2.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Resolved implements InferState {
    private final List<Class<?>> clazz;

    public Resolved(Class<?> clazz) {
        this(Arrays.asList(clazz));
    }

    public Resolved(List<? extends Class<?>> clazz) {
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
        return String.format("Resolved(%s)", String.join(" | ", this.clazz.stream().map(Class::getCanonicalName).collect(Collectors.toList())));
    }

    @Override
    public InferStateType getInferStateType() {
        return InferStateType.RESOLVED;
    }

}
