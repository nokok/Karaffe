package org.karaffe.compiler.frontend.karaffe.transformer;

public interface Phase<I, O> {

    public String phaseName();

    public String phaseDescription();

    O run(I input);

    boolean changed();

}
