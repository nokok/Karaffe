package org.karaffe.compiler.phases;

public abstract class AbstractTransformer<I, O> implements Transformer<I, O> {

    private final Class<I> inputType;
    private final Class<O> outputType;

    public AbstractTransformer(final Class<I> inputType, final Class<O> outputType) {
        this.inputType = inputType;
        this.outputType = outputType;
    }

    @Override
    public Class<I> getInputType() {
        return this.inputType;
    }

    @Override
    public Class<O> getOutputType() {
        return this.outputType;
    }

}
