package org.karaffe.compiler.tree.transform;

import java.util.Objects;

import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;

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
