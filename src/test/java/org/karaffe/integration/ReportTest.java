package org.karaffe.integration;

import org.karaffe.compiler.CompilerConstants;
import org.karaffe.compiler.phase.Phases;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.StartupEnv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportTest {

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void compilerVersion() {
        CompilerContext ctx = CompilerContext.createInitialContext(StartupEnv.create(new String[]{"--version"}, java.util.Collections.emptyMap()));
        Phases phases = Phases.createPhasesFromContext(ctx);
        phases.executeAll(ctx);

        assertEquals(1, ctx.getReports().size());
        assertEquals("Karaffe compiler version: " + CompilerConstants.VERSION, ctx.getReports().get(0).getHeader());
    }

    @Test
    void fileNotFound() {
        CompilerContext ctx = CompilerContext.createInitialContext(StartupEnv.create(new String[]{"Invalid.krf"}, java.util.Collections.emptyMap()));
        Phases phases = Phases.createPhasesFromContext(ctx);
        phases.executeAll(ctx);

        assertEquals(2, ctx.getReports().size());
        assertEquals("file not found: Invalid.krf", ctx.getReports().get(0).getHeader());
    }
}
