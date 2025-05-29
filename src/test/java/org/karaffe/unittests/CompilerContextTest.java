package org.karaffe.unittests;

import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.StartupEnv;
import org.junit.jupiter.api.Test;

import java.util.Collections; // For emptyMap

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CompilerContextTest {

    @Test
    void createContext() {
        // setup:
        StartupEnv env = StartupEnv.create(new String[]{}, Collections.emptyMap());
        CompilerContext ctx = CompilerContext.createInitialContext(env);

        // expect:
        assertNotNull(ctx);
    }

    @Test
    void contextIsEmptyArgs1() { // Renamed from context#isEmptyArgs1
        // setup:
        StartupEnv env = StartupEnv.create(new String[]{}, Collections.emptyMap());
        CompilerContext ctx = CompilerContext.createInitialContext(env);

        // expect:
        assertTrue(ctx.isEmptyArgs());
    }

    @Test
    void contextIsNotEmptyArgs2() { // Renamed from context#isEmptyArgs2 and reflects assertion
        // setup:
        StartupEnv env = StartupEnv.create(new String[]{"--help"}, Collections.emptyMap());
        CompilerContext ctx = CompilerContext.createInitialContext(env);

        // expect:
        assertFalse(ctx.isEmptyArgs());
    }
}
