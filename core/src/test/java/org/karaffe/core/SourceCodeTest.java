package org.karaffe.core;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;
import org.karaffe.core.io.readers.RawSourceCodeReadable;
import org.karaffe.core.io.readers.SourceCodeReaderFactory;

public class SourceCodeTest {
    @Test
    public void testFromPath() {
        Path emptyKrfFilePath = PathUtil.testResourcesDir().resolve("Empty.krf");
        SourceCode sourceCode = new SourceCode(emptyKrfFilePath);
        assertEquals("Empty.krf", sourceCode.name());
        assertEquals(emptyKrfFilePath.toAbsolutePath().toString(), sourceCode.toPath());
        assertEquals("", sourceCode.body());
    }

    @Test
    public void testFromPathWithSource() {
        SourceCode sourceCode = new SourceCode(PathUtil.testResourcesDir().resolve("Source.krf"));
        RawSourceCodeReadable<Path> fileSystem = SourceCodeReaderFactory.fromFileSystemRaw();
        Optional<String> s = fileSystem.read(PathUtil.testResourcesDir().resolve("Source.krf"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCR() {
        new SourceCode(Paths.get("path"), "i\r\n");
    }

    @Test
    public void testToString() {
        SourceCode sourceCode = new SourceCode(Paths.get(""), "s");
        assertEquals("s", sourceCode.toString());
    }

    @Test
    public void testOffsetToLine() {
        SourceCode sourceCode = new SourceCode(Paths.get(""), "1\n3");
        assertEquals(1, sourceCode.offsetToLine(0));
        assertEquals(1, sourceCode.offsetToLine(1));
        assertEquals(2, sourceCode.offsetToLine(2));
    }

}
