package it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.KaraffeCompiler;

public class KaraffeCompilerTest {
    @Test(expected = NullPointerException.class)
    public void testKaraffeCompiler() {
        new KaraffeCompiler(null);
    }

    @Test
    public void testEmptyImput() throws Exception {
        KaraffeCompiler compiler = new KaraffeCompiler(new String[] {});
        assertEquals(0, (int) compiler.call());
    }
}
