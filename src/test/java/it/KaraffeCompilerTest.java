package it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.KaraffeCompiler;
import org.karaffe.compiler.context.CompilerConfig;

public class KaraffeCompilerTest {
    @Test
    public void testEmptyImput() throws Exception {
        final KaraffeCompiler compiler = new KaraffeCompiler(CompilerConfig.buildConfig(new String[] {}));
        assertEquals(0, (int) compiler.call());
    }

    @Test(expected = NullPointerException.class)
    public void testKaraffeCompiler() {
        new KaraffeCompiler(null);
    }
}
