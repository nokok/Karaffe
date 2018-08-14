package org.karaffe.compiler.base.mir.instance;

import org.karaffe.compiler.base.attr.Attribute;

import java.util.Objects;

public class NewInstance extends Attribute {
    private Class<?> clazz;

    public NewInstance(Class<?> clazz) {
        this.clazz = Objects.requireNonNull(clazz);
    }

    @Override
    public String toString() {
        return "NewInstance " + clazz.getCanonicalName();
    }
}
