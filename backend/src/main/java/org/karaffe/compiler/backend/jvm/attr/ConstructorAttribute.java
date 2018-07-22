package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.mir.util.attr.Attribute;

import java.lang.reflect.Constructor;
import java.util.Objects;

public class ConstructorAttribute extends Attribute {
    private Constructor<?> constructor;

    public ConstructorAttribute(Constructor<?> constructor) {
        this.constructor = Objects.requireNonNull(constructor);
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }
}
