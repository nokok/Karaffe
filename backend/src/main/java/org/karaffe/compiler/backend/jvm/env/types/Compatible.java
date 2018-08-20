package org.karaffe.compiler.backend.jvm.env.types;

import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.Objects;

public class Compatible {
    private Label label;
    private Class<?> clazz;

    public Compatible(Label label, Class<?> clazz) {
        this.label = Objects.requireNonNull(label);
        this.clazz = Objects.requireNonNull(clazz);
    }

    @Override
    public String toString() {
        return label + " is compatible for " + clazz;
    }
}
