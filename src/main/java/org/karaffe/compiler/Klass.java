package org.karaffe.compiler;

import java.lang.reflect.Method;
import java.util.Objects;

public class Klass {

    private final Class<?> clazz;

    public Klass(Class<?> clazz) {
        this.clazz = Objects.requireNonNull(clazz);
    }

    public boolean isArithmeticOperatorApplicable() {
        if (clazz.equals(boolean.class) || clazz.equals(char.class)) {
            return false;
        }
        if (clazz.isPrimitive()) {
            return true;
        }
        try {
            Method plus = clazz.getMethod("plus", clazz);
            return plus.getReturnType().equals(clazz);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
