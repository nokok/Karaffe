package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;

public class ExprParserTest {
    @Test
    public void testExpr() {
        this.runTest("1 + 1", true);
    }

    @Test
    public void test2() {
        this.runTest("i[0]", true);
    }

    @Test
    public void test3() {
        this.runTest("i.length", true);
    }

    @Test
    public void test4() {
        this.runTest("i.foo(1)", true);
    }

    @Test
    public void test5() {
        this.runTest("i.foo(1,2)", true);
    }

    @Test
    public void test6() {
        this.runTest("i.foo(1,2,new int[0])", true);
    }

    @Test
    public void test7() {
        this.runTest("1", true);
    }

    @Test
    public void test8() {
        this.runTest("true", true);
    }

    @Test
    public void test9() {
        this.runTest("false", true);
    }

    @Test
    public void test10() {
        this.runTest("abc", true);
    }

    @Test
    public void test11() {
        this.runTest("this", true);
    }

    @Test
    public void test12() {
        this.runTest("new int[0]", true);
    }

    @Test
    public void test13() {
        this.runTest("new int[1 + 4]", true);
    }

    @Test
    public void test14() {
        this.runTest("new String()", true);
    }

    @Test
    public void test15() {
        this.runTest("!true", true);
    }

    @Test
    public void test16() {
        this.runTest("(1)", true);
    }

    @Test
    public void test17() {
        this.runTest("((1 + 2)+ 3 * 5)", true);
    }

    @Test
    public void test18() {
        this.runTest("(1+2)*3", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final ExprParser parser = new ExprParser();
        final MatchResult result = parser.parse(lexer.run());
        Assert.assertEquals(source + " " + result, v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            final MatchResult eofResult = new EOFParser().match(result.next());
            if (eofResult.isFailure()) {
                Assert.fail(eofResult.toString());
            }
            Assert.assertEquals(0, eofResult.next().size());
        }
    }
}
