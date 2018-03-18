package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.context.ConfigKeys.FlagConfigs;
import org.karaffe.compiler.context.ConfigKeys.StringConfigs;

public class CompilerConfigTest {

    @Test
    public void testBasicConfig() throws Exception {
        final CompilerConfig config = CompilerConfig.buildConfig(new String[] { "-version", "--logLevel=DEBUG" });
        final Optional<Boolean> valueOpt = config.getFlag(FlagConfigs.SHOW_VERSION);
        assertTrue(valueOpt.isPresent());
        assertTrue(valueOpt.get().booleanValue());
        final Optional<String> logLevelOpt = config.getValue(StringConfigs.LOG_LEVEL);
        assertTrue(logLevelOpt.isPresent());
        assertEquals("DEBUG", logLevelOpt.get());
    }

    @Test
    public void testStringKeyConfig() throws Exception {
        final CompilerConfig compilerConfig = CompilerConfig.buildConfig(new String[] { "--compiler.insertEOF=false", "--compiler.autoCRLFtoLF=true" });
        assertFalse(compilerConfig.getFlag(FlagConfigs.COMPILER_INSERT_EOF).get());
        assertTrue(compilerConfig.getFlag(FlagConfigs.COMPILER_AUTO_CRLF_TO_LF).get());
    }

}
