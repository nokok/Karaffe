package ut;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.base.util.DiagnosticInfo;

public class DiagnosticInfoTest {
    @Test
    public void testMethods() {
        final DiagnosticInfo info = DiagnosticInfo.INSTANCE;
        assertEquals(System.getProperty("java.version"), info.javaVersion());
        assertEquals(System.getProperty("java.vendor"), info.vendor());
        assertEquals(System.getProperty("java.vm.specification.version"), info.vmSpecVersion());
        assertEquals(System.getProperty("os.name"), info.osName());
        assertEquals(System.getProperty("os.arch"), info.osArch());
        assertEquals(System.getProperty("os.version"), info.osVersion());
        assertEquals(System.getProperty("file.encoding"), info.fileEncoding());
    }
}
