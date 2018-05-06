package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.transformer.util.PhaseResult;

public class CompilerPreConditionChecker implements Phase<Options, Void> {
    @Override
    public String phaseName() {
        return "compiler-precondition";
    }

    @Override
    public String phaseDescription() {
        return "Check precondition";
    }

    @Override
    public PhaseResult<Void> run(Options input) {
        try {
            Class.forName("karaffe.core.Any");
            return PhaseResult.onSuccess();
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("", e);
        }
    }

    @Override
    public boolean changed() {
        return false;
    }
}
