package org.karaffe.unittests;

import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.StartupEnv;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CompilerContextTest {

    @Test
    void createContext() {
        StartupEnv env = StartupEnv.create(new String[]{}, Collections.emptyMap());
        CompilerContext ctx = CompilerContext.createInitialContext(env);

        assertNotNull(ctx);
    }

    @Test
    void isEmptyArgsWhenNoArgs() {
        StartupEnv env = StartupEnv.create(new String[]{}, Collections.emptyMap());
        CompilerContext ctx = CompilerContext.createInitialContext(env);

        assertTrue(ctx.isEmptyArgs());
    }

    @Test
    void isNotEmptyArgsWhenHelpArgIsPresent() {
        StartupEnv env = StartupEnv.create(new String[]{"--help"}, Collections.emptyMap());
        CompilerContext ctx = CompilerContext.createInitialContext(env);

        assertFalse(ctx.isEmptyArgs());
    }
}
