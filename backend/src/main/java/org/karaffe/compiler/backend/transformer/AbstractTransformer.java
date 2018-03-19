package org.karaffe.compiler.backend.transformer;

import java.util.Objects;

import org.karaffe.compiler.backend.transformer.api.BaseDefaultTransformer;

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
