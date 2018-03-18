package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Failure;

public class ParseFailTest {
    private void runTest(final String source, final int line, final int column) {
        final KaraffeParser parser = new KaraffeParser();
        final MatchResult result = parser.parse(source);
        assertTrue(result.isFailure());
        final Failure failure = result.toFailure().get();
        assertFalse(failure.getNode().isPresent());
        final Token errorToken = failure.errorHeadF().get();
        assertEquals(line, errorToken.getPosition().getLineNumber().get().intValue());
        assertEquals(column, errorToken.getPosition().getColNumber().get().intValue());
    }

    @Test
    public void test1() {
        this.runTest("package ;", 1, 8);
    }

    @Test
    public void test2() {
        this.runTest("package foo.", 1, 11);
    }

    @Test
    public void test3() {
        this.runTest("package foo;\n"
                + "class A {", 2, 8);
    }
}
