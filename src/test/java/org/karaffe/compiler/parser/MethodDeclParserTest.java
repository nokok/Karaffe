package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;

public class MethodDeclParserTest {

    @Test
    public void test1() {
        this.runTest("public int doSomething() {\n"
                + "return 1;"
                + "}", true);
    }

    @Test
    public void test2() {
        this.runTest("public int doSomething() {\n"
                + "int a;"
                + "return 1;"
                + "}", true);
    }

    @Test
    public void test3() {
        this.runTest("public int doSomething() {\n"
                + "int a;"
                + "String b;"
                + "return 1;"
                + "}", true);
    }

    @Test
    public void test4() {
        this.runTest("public int doSomething() {\n"
                + "  if (true) {\n"
                + "    a = 1;\n"
                + "  } else {\n"
                + "    b = 1;\n"
                + "  }\n"
                + "  return 1;"
                + "}", true);
    }

    @Test
    public void test5() {
        this.runTest("public int doSomething() {\n"
                + "int a;"
                + "if (true) { a = 1;} else {b = 1;}"
                + "return 1;"
                + "}", true);
    }

    @Test
    public void test6() {
        this.runTest("     public int doSomething(int a) {\n"
                + "       return a;\n"
                + "     }\n", true);
    }

    @Test
    public void test7() {
        this.runTest("     public int doSomething() {\n"
                + "       return a;\n"
                + "     }\n", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser parser = new MethodDefParser();
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
