package org.karaffe.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;

public class RawSourceCodeReadablesTest {
    @Test
    public void testRead() {
        LocalFileSystemSourceCodeReader reader = new LocalFileSystemSourceCodeReader();
        Optional<String> mayBeSource = reader.read(PathUtil.testResourcesDir().resolve(Paths.get("Empty.krf")));
        assertTrue(mayBeSource.isPresent());
        String sourceCode = mayBeSource.get();
        assertEquals(0, sourceCode.length());
    }
}
