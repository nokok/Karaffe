package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.attr.Attribute;

import java.util.Objects;

public class ClassAttribute extends Attribute {
    private Class<?> clazz;

    public ClassAttribute(Class<?> clazz) {
        this.clazz = Objects.requireNonNull(clazz);
    }

    public Class<?> getClassObject() {
        return this.clazz;
    }
}
