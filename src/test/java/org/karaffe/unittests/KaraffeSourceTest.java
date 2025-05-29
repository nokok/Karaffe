package org.karaffe.unittests;

import org.karaffe.compiler.util.KaraffeSource;
import org.karaffe.compiler.util.Platform;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir; // For temporary directory

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; // For CharBuffer comparison

class KaraffeSourceTest {

    @Test
    void toStringMethod() {
        assertEquals("Source", KaraffeSource.fromString("Source").toString());
    }

    @Test
    void testEquals() {
        // KaraffeSource itself might not have an equals method,
        // the original test compares CharBuffer wrappers.
        // This means KaraffeSource likely implements CharSequence.
        KaraffeSource sourceA1 = KaraffeSource.fromString("A");
        KaraffeSource sourceA2 = KaraffeSource.fromString("A");
        // Direct CharBuffer comparison as in Spock:
        assertEquals(CharBuffer.wrap(sourceA1), CharBuffer.wrap(sourceA2));
        // Also good to check if KaraffeSource itself can be compared if it implements CharSequence
        // assertEquals(sourceA1, sourceA2); // This would only work if KaraffeSource has a proper equals method.
    }

    @Test
    void fromPath(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("Source.krf");
        Files.write(tempFile, "class Hoge {}".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        
        KaraffeSource p = KaraffeSource.fromPath(tempFile);
        // Files.delete(tempFile) is not strictly necessary with @TempDir as JUnit manages it,
        // but it's good practice if not using @TempDir or for specific cleanup.
        // Here, @TempDir handles cleanup.

        assertEquals(tempFile.toString(), p.getSourceName());
    }

    @Test
    void fromRelativePath() throws IOException { // Added throws IOException as fromPath can throw it
        // Note: This test assumes 'src/test/resources/Main.krf' exists and is accessible.
        // This path is relative to the project root.
        Path relativePath = Paths.get("src/test/resources/Main.krf");
        KaraffeSource p = KaraffeSource.fromPath(relativePath);

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
