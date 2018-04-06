package org.karaffe.compiler.transformer;

import org.karaffe.compiler.transformer.api.BaseDefaultTransformer;

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

}
