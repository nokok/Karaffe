package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Failure;

public class ParseFailTest {
    @Test
    public void test1() {
        runTest("package ;", 1, 8);
    }

    @Test
    public void test2() {
        runTest("package foo.", 1, 11);
    }

    @Test
    public void test3() {
        runTest("package foo;\n"
                + "class A {", 2, 8);
    }

    private void runTest(String source, int line, int column) {
        KaraffeParser parser = new KaraffeParser();
        MatchResult result = parser.parse(new KaraffeLexer(source).run());
        assertTrue(result.isFailure());
        Failure failure = result.toFailure().get();
        assertFalse(failure.getNode().isPresent());
        Token errorToken = failure.errorHeadF().get();
        assertEquals(line, errorToken.getPosition().getLineNumber().get().intValue());
        assertEquals(column, errorToken.getPosition().getColNumber().get().intValue());
    }
}
