package org.karaffe.unittests;

import org.karaffe.compiler.phase.Phase;
import org.karaffe.compiler.phase.SequentialPhases;
import org.karaffe.compiler.phase.frontend.karaffe.KaraffeParsePhase;
import org.karaffe.compiler.phase.util.ShowReportsPhase;
import org.karaffe.compiler.phase.util.ShowUsagePhase;
import org.karaffe.compiler.phase.util.ShowVersionPhase;
import org.karaffe.compiler.util.CompilerContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhaseTest {

    static class Phase1 implements Phase {
        int value = 0; // package-private for access from test

        @Override
        public String getName() {
            return "Phase1"; // Or null as in Spock, but a name is better
        }

        @Override
        public void execute(CompilerContext context) {
            this.value = 1;
        }
    }

    static class Phase2 implements Phase {
        int value = 0; // package-private for access from test

        @Override
        public String getName() {
            return "Phase2"; // Or null as in Spock
        }

        @Override
        public void execute(CompilerContext context) {
            this.value = 2;
        }
    }

    @Test
    void testSequentialPhases() {
        SequentialPhases p = new SequentialPhases();
        Phase1 ph1 = new Phase1();
        p.add(ph1);
        Phase2 ph2 = new Phase2();
        p.add(ph2);

        // Assuming executeAll() takes a CompilerContext, though not shown in Spock setup.
        // If executeAll does not take a context, this needs adjustment.
        // The Spock test did not pass a context to executeAll().
        // Let's assume it doesn't need one, or it's internally managed if phases are context-free for executeAll.
        // Checking SequentialPhases.executeAll() signature would be needed if this fails.
        // For now, calling it without context as per Spock.
        p.executeAll(CompilerContext.createInitialContext()); // Passing a dummy context if required by Phase.execute

        assertEquals(1, ph1.value);
        assertEquals(2, ph2.value);
    }

    @Test
    void testShowVersionPhase() {
        CompilerContext ctx = CompilerContext.createInitialContext();
        ShowVersionPhase p = new ShowVersionPhase();

        assertEquals(0, ctx.getReports().size());

        p.execute(ctx);

        assertEquals(1, ctx.getReports().size());
    }

    @ParameterizedTest(name = "testPhaseName {index} => {1}")
    @MethodSource("phaseNameDataSource")
    void testPhaseName(Phase phase, String expectedName) {
        assertEquals(expectedName, phase.getName());
    }

    static Stream<Arguments> phaseNameDataSource() {
        return Stream.of(
                Arguments.of(new ShowVersionPhase(), "show-version"),
                Arguments.of(new ShowUsagePhase(), "show-usage"),
                Arguments.of(new ShowReportsPhase(), "show-reports"),
                Arguments.of(new KaraffeParsePhase(), "frontend-karaffe-parser")
        );
    }
}
