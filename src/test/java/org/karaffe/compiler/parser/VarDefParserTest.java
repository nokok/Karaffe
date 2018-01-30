package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.parser.util.MatchResult;

public class VarDefParserTest {
    @Test
    public void testEmpty() {
        this.runTest("", false);
    }

    @Test
    public void test1() {
        this.runTest("let a: Int = 1", true);
        this.runTest("var a: Int = 1", true);
        this.runTest("var a: Int", true);
        this.runTest("var a", true);
        this.runTest("let a = 1", true);
        this.runTest("var a = 1", true);
        this.runTest("def a: Int", false);
    }

    private void runTest(final String source, final boolean v) {
        final Parser varDeclParser = new VarDefParser();
        final MatchResult result = varDeclParser.parse(source);
        Assert.assertEquals(result.toString(), v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            final MatchResult eofResult = new EOFParser().parse(result.next());
            if (eofResult.isFailure()) {
                Assert.fail(eofResult.toString());
            }
            Assert.assertEquals(0, eofResult.next().size());
        }
    }
}
