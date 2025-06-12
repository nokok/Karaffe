package org.karaffe.unittests;

import org.karaffe.compiler.util.StartupEnv;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StartupEnvTest {

    @Test
    void commandLineArgsTest() {
        StartupEnv env = StartupEnv.create(new String[]{"a"}, Collections.emptyMap());
        assertEquals("a", env.getCommandLineArgs()[0]);
    }

    @Test
    void envTest() {
        Map<String, String> envMap = new HashMap<>();
        envMap.put("HOGE", "HOGE");
        StartupEnv env = StartupEnv.create(new String[]{}, envMap);

        assertTrue(env.getEnv("HOGE").isPresent());
        assertFalse(env.getEnv("FUGA").isPresent());
    }
}
