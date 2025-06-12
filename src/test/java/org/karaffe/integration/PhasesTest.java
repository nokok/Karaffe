package org.karaffe.integration;

import org.karaffe.compiler.phase.Phases;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.StartupEnv;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PhasesTest {

    @Test
    void version() {
        CompilerContext ctx = CompilerContext.createInitialContext(StartupEnv.create(new String[]{"--version"}, java.util.Collections.emptyMap()));
        Phases phases = Phases.createPhasesFromContext(ctx);

        assertTrue(phases.getPhase("show-version").isPresent());
    }

    @Test
    void reporting() {
        CompilerContext ctx = CompilerContext.createInitialContext(StartupEnv.create(new String[]{}, java.util.Collections.emptyMap()));
        Phases phases = Phases.createPhasesFromContext(ctx);

        assertTrue(phases.getPhase("show-reports").isPresent());
    }
}
