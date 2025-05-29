package org.karaffe.unittests;

import org.karaffe.compiler.util.KaraffeSource;
import org.junit.jupiter.api.Test;

import java.io.IOException; // For potential IOException from fromPath
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathNameTest {

    @Test
    void karaffeSourceGetSourceName() throws IOException { // Renamed and added throws
        // setup:
        // This test assumes 'src/test/resources/Main.krf' exists and is accessible
        // relative to the project root.
        KaraffeSource s = KaraffeSource.fromPath(Paths.get("src/test/resources/Main.krf"));

        // expect:
        assertEquals("src/test/resources/Main.krf", s.getSourceName());
    }
}
