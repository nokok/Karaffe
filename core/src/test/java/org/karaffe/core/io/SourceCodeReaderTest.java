package org.karaffe.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;
import org.karaffe.core.PathUtil;
import org.karaffe.core.SourceCode;
import org.karaffe.core.io.readers.RawSourceCodeReadable;
import org.karaffe.core.io.readers.SourceCodeReadable;
import org.karaffe.core.io.readers.SourceCodeReaderFactory;

public class SourceCodeReaderTest {
    @Test
    public void testFromFileSystemRaw() {
        RawSourceCodeReadable<Path> reader = SourceCodeReaderFactory.fromFileSystemRaw();
        Optional<String> mayBeSource = reader.read(PathUtil.testResourcesDir().resolve(Paths.get("Empty.krf")));
        assertTrue(mayBeSource.isPresent());
        String sourceCode = mayBeSource.get();
        assertEquals(0, sourceCode.length());
    }

    @Test
    public void testDirect() {
        RawSourceCodeReadable<String> reader = SourceCodeReaderFactory.directRaw();
        assertEquals("\n", reader.read("\r\n").get());
        assertEquals("\n", reader.read("\r").get());
        assertEquals("\n", reader.read("\n").get());
    }

    @Test
    public void testFromFileSystem() {
        SourceCodeReadable<Path> reader = SourceCodeReaderFactory.fromFileSystem();
        Optional<SourceCode> mayBeSource = reader.readAsSourceCode(PathUtil.testResourcesDir().resolve(Paths.get("Empty.krf")));
        assertTrue(mayBeSource.isPresent());
        SourceCode sourceCode = mayBeSource.get();
        assertEquals(0, sourceCode.length());
    }

    @Test
    public void testFromFileSystemNoFile() {
        SourceCodeReadable<Path> reader = SourceCodeReaderFactory.fromFileSystem();
        Optional<SourceCode> mayBeSource = reader.readAsSourceCode(PathUtil.testResourcesDir().resolve(Paths.get("None.krf")));
        assertFalse(mayBeSource.isPresent());
    }

}
