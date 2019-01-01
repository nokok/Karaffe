package org.karaffe.compiler.tree.attr;

import java.util.Objects;

public class SuperClass extends Attribute {
    private Class<?> superClass;

    public SuperClass() {
        this(Object.class);
    }

    public SuperClass(Class<?> superClass) {
        this.superClass = Objects.requireNonNull(superClass);
    }

    @Override
    public String toString() {
        return "SuperClass=" + this.superClass.getCanonicalName();
    }
}
