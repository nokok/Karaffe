package org.karaffe.unittests;

import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.util.Platform;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KaraffeSourceTest {

    @Test
    void toStringMethod() {
        assertEquals("Source", KaraffeSource.fromString("Source").toString());
    }

    @Test
    void testEquals() {
        assertEquals(CharBuffer.wrap(KaraffeSource.fromString("A")), CharBuffer.wrap(KaraffeSource.fromString("A")));
    }

    @Test
    void fromPath() throws IOException {
        Path path = Paths.get("Source.krf");
        try {
            Files.write(path, "class Hoge {}".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            KaraffeSource p = KaraffeSource.fromPath(path);
            assertEquals("Source.krf", p.getSourceName());
        } finally {
            Files.deleteIfExists(path);
        }
    }

    @Test
    void fromRelativePath() throws IOException {
        // Assuming src/test/resources/Main.krf exists as per the original test context
        Path path = Paths.get("src/test/resources/Main.krf");
        KaraffeSource p = KaraffeSource.fromPath(path);

        String expectedName;
        if (Platform.isWindows()) {
            expectedName = "src\\test\\resources\\Main.krf";
        } else {
            expectedName = "src/test/resources/Main.krf";
        }
        assertEquals(expectedName, p.getSourceName());
    }

    @Test
    void testGetCodeByLine() {
        KaraffeSource s = KaraffeSource.fromString("0\n1");

        assertEquals("0", s.getCodeByLine(1));
        assertEquals("1", s.getCodeByLine(2));
        assertEquals("<EOF>", s.getCodeByLine(3));
    }
}
