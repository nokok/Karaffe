package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.context.ConfigKeys.FlagConfigs;
import org.karaffe.compiler.context.ConfigKeys.StringConfigs;
import org.karaffe.compiler.exceptions.UnrecognizedOptionException;

public class CompilerConfigTest {

    @Test
    public void testBasicConfig() throws Exception {
        CompilerConfig config = CompilerConfig.buildConfig(new String[] { "-version", "--logLevel=DEBUG" });
        Optional<Boolean> valueOpt = config.getFlag(FlagConfigs.SHOW_VERSION);
        assertTrue(valueOpt.isPresent());
        assertTrue(valueOpt.get().booleanValue());
        Optional<String> logLevelOpt = config.getValue(StringConfigs.LOG_LEVEL);
        assertTrue(logLevelOpt.isPresent());
        assertEquals("DEBUG", logLevelOpt.get());
    }

    @Test
    public void testStringKeyConfig() throws Exception {
        CompilerConfig compilerConfig = CompilerConfig.buildConfig(new String[] { "--compiler.insertEOF=false", "--compiler.autoCRLFtoLF=true" });
        assertFalse(compilerConfig.getFlag(FlagConfigs.COMPILER_INSERT_EOF).get());
        assertTrue(compilerConfig.getFlag(FlagConfigs.COMPILER_AUTO_CRLF_TO_LF).get());
    }

    @Test(expected = UnrecognizedOptionException.class)
    public void testUnrecognizedOption() throws Exception {
        CompilerConfig config = CompilerConfig.buildConfig(new String[] { "-help" });
    }

}
