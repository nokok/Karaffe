package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.util.CommandLineArgPart;

public class CommandLineArgPartTest {
    @Test
    public void testBasicArg() {
        this.testPart("-v", false, false, "v");
    }

    @Test
    public void testFullArg() {
        this.testPart("--version", false, true, "version");
    }

    @Test
    public void testInvalidShortArg() {
        final CommandLineArgPart part = this.testPart("-", false, false, "");
        assertFalse(part.isValidFormat());
    }

    private CommandLineArgPart testPart(final String arg, final boolean isValueConfig, final boolean isFullArg, final String expectedCmd) {
        final CommandLineArgPart part = new CommandLineArgPart(arg);
        assertEquals(isValueConfig, part.isValueConfigurator());
        assertEquals(!isValueConfig, part.isFlagConfigrator());
        assertEquals(isFullArg, part.isFullArg());
        assertEquals(!isFullArg, part.isShortArg());
        assertEquals(expectedCmd, part.getCmd());
        return part;
    }

    private CommandLineArgPart testPart(final String arg, final boolean isValueConfig, final boolean isFullArg, final String expectedCmd, final String expectedValue) {
        final CommandLineArgPart part = new CommandLineArgPart(arg);
        assertEquals(isValueConfig, part.isValueConfigurator());
        assertEquals(!isValueConfig, part.isFlagConfigrator());
        assertEquals(isFullArg, part.isFullArg());
        assertEquals(!isFullArg, part.isShortArg());
        assertEquals(expectedCmd, part.getCmd());
        assertEquals(expectedValue, part.getValue().get());
        assertTrue(part.isValidFormat());
        return part;
    }

    @Test
    public void testShortArg() {
        this.testPart("-version", false, false, "version");
    }

    @Test
    public void testValueConfigurator() {
        this.testPart("--key=value", true, true, "key", "value");
    }
}
