package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.parser.util.MatchResult;

public class MainClassDeclParserTest {
    @Test
    public void testEmpty() {
        this.runTest("", false);
    }

    @Test
    public void test1() {
        this.runTest("class A{}", false);
    }

    @Test
    public void test2() {
        this.runTest("class A{\n"
                + "  public static void main(String[] args) {\n"
                + "    System.out.println(1+2);"
                + "  }"
                + "}", true);
    }

    private void runTest(final String source, final boolean v) {
        final Parser parser = new MainClassDeclParser();
        final MatchResult result = parser.parse(source);
        Assert.assertEquals(source + " " + result, v, result.isSuccess());
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
