package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.frontend.karaffe.transformer.api.BaseDefaultTransformer;

import java.util.Objects;

public abstract class AbstractTransformer implements BaseDefaultTransformer {

    private final String name;

    public AbstractTransformer(String transformerName) {
        this.name = Objects.requireNonNull(transformerName);
    }

    @Override
    public String getTransformerName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTransformer that = (AbstractTransformer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
