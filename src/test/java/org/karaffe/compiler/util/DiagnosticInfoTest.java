package org.karaffe.compiler.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DiagnosticInfoTest {
    @Test
    public void testMethods() {
        DiagnosticInfo info = DiagnosticInfo.INSTANCE;
        assertEquals(System.getenv("java.version"), info.javaVersion());
        assertEquals(System.getenv("java.vendor"), info.vendor());
        assertEquals(System.getenv("java.vm.specification.version"), info.vmSpecVersion());
        assertEquals(System.getenv("os.name"), info.osName());
        assertEquals(System.getenv("os.arch"), info.osArch());
        assertEquals(System.getenv("os.version"), info.osVersion());
        assertEquals(System.getenv("file.encoding"), info.fileEncoding());
    }
}
