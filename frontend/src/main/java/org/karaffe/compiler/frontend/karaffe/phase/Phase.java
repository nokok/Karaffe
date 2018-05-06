package org.karaffe.compiler.frontend.karaffe.phase;

import org.karaffe.compiler.frontend.karaffe.transformer.util.PhaseResult;

public interface Phase<I, O> {

    public String phaseName();

    public String phaseDescription();

    PhaseResult<O> run(I input);

    boolean changed();

}
