package it;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashSet;
import java.util.stream.Stream;

import org.junit.Test;
import org.karaffe.compiler.CompilerContext;

public class CompilerContextTest {

    @Test
    public void testDefaultCompilerContext() {
        CompilerContext compilerContext = CompilerContext.defaultContext(new HashSet<>());
        assertFalse(compilerContext.hasSource());
        Stream<File> stream = compilerContext.sourceStream();
        stream.forEach(f -> fail());
    }
}
