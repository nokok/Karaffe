package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.util.CommandLineArgPart;

public class CommandLineArgPartTest {
    @Test
    public void testFullArg() {
        testPart("--version", false, true, "version");
    }

    @Test
    public void testShortArg() {
        testPart("-version", false, false, "version");
    }

    @Test
    public void testValueConfigurator() {
        testPart("--key=value", true, true, "key", "value");
    }

    @Test
    public void testBasicArg() {
        testPart("-v", false, false, "v");
    }

    @Test
    public void testInvalidShortArg() {
        CommandLineArgPart part = testPart("-", false, false, "");
        assertFalse(part.isValidFormat());
    }

    private CommandLineArgPart testPart(String arg, boolean isValueConfig, boolean isFullArg, String expectedCmd) {
        CommandLineArgPart part = new CommandLineArgPart(arg);
        assertEquals(isValueConfig, part.isValueConfigurator());
        assertEquals(!isValueConfig, part.isFlagConfigrator());
        assertEquals(isFullArg, part.isFullArg());
        assertEquals(!isFullArg, part.isShortArg());
        assertEquals(expectedCmd, part.getCmd());
        return part;
    }

    private CommandLineArgPart testPart(String arg, boolean isValueConfig, boolean isFullArg, String expectedCmd, String expectedValue) {
        CommandLineArgPart part = new CommandLineArgPart(arg);
        assertEquals(isValueConfig, part.isValueConfigurator());
        assertEquals(!isValueConfig, part.isFlagConfigrator());
        assertEquals(isFullArg, part.isFullArg());
        assertEquals(!isFullArg, part.isShortArg());
        assertEquals(expectedCmd, part.getCmd());
        assertEquals(expectedValue, part.getValue().get());
        assertTrue(part.isValidFormat());
        return part;
    }
}
