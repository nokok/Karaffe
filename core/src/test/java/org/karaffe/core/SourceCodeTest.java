package org.karaffe.core;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.junit.Test;

public class SourceCodeTest {
    @Test
    public void testFromPath() {
        Path emptyKrfFilePath = PathUtil.testResourcesDir().resolve("Empty.krf");
        SourceCode sourceCode = new SourceCode(emptyKrfFilePath);
        assertEquals("Empty.krf", sourceCode.name());
        assertEquals(emptyKrfFilePath.toAbsolutePath().toString(), sourceCode.toPath());
        assertEquals("", sourceCode.body());
    }
}
