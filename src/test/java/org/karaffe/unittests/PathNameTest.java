package org.karaffe.unittests;

import org.karaffe.compiler.util.KaraffeSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathNameTest {

    @Test
    void testGetSourceName() throws IOException {
        // Assuming src/test/resources/Main.krf exists as per the original test context
        KaraffeSource s = KaraffeSource.fromPath(Paths.get("src/test/resources/Main.krf"));

        assertEquals("src/test/resources/Main.krf", s.getSourceName());
    }
}
