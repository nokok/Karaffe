package org.karaffe.compiler.tree.attr;

import java.util.Objects;

public class InferredType extends Attribute {
    private Class<?> inferredType;

    public InferredType(Class<?> inferredType) {
        this.inferredType = Objects.requireNonNull(inferredType);
    }

    @Override
    public String toString() {
        return "InferredType=" + inferredType.getCanonicalName();
    }
}
