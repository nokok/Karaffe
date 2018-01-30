package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;

public class MethodDefParserTest {
    @Test
    public void testEmpty() {
        this.runTest("", false);
    }

    @Test
    public void test1() {
        this.runTest("public int doSomething() {\n"
                + "return 1;"
                + "}", true);
    }

    @Test
    public void test2() {
        this.runTest("public int doSomething() {\n"
                + "var a :int;"
                + "return 1;"
                + "}", true);
    }

    @Test
    public void test3() {
        this.runTest("public int doSomething() {\n"
                + "var a :int;"
                + "var b :String;"
                + "return 1;"
                + "}", true);
    }

    @Test(timeout = 1000)
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

    @Test(timeout = 1000)
    public void test5() {
        this.runTest("public int doSomething() {\n"
                + "var a :int;"
                + "if (true) { a = 1;} else {b = 1;}"
                + "return 1;"
                + "}", true);
    }

    @Test(timeout = 1000)
    public void test6() {
        this.runTest("     public int doSomething(int a) {\n"
                + "       return a;\n"
                + "     }\n", true);
    }

    @Test(timeout = 1000)
    public void test7() {
        this.runTest("     public int doSomething() {\n"
                + "       return a;\n"
                + "     }\n", true);
    }

    @Test(timeout = 1000)
    public void test8() {
        this.runTest("public", false);
    }

    @Test(timeout = 1000)
    public void test9() {
        this.runTest("public private", false);
    }

    @Test(timeout = 1000)
    public void test10() {
        this.runTest("void", false);
    }

    @Test
    public void test11() {
        this.runTest("public int int", false);
    }

    @Test
    public void test12() {
        this.runTest("public int doSomething{", false);
    }

    @Test
    public void test13() {
        this.runTest("public int doSomething(int)", false);
    }

    @Test
    public void test14() {
        this.runTest("public int doSomething(int|", false);
    }

    @Test
    public void test15() {
        this.runTest("public int doSomething(int| {", false);
    }

    @Test
    public void test16() {
        this.runTest("    public int computeFac(int num){\n" +
                "        var numAux :int ;\n" +
                "        if (num < 1) {\n" +
                "            numAux = 1 ;\n" +
                "        } else {\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                "        }" +
                "        return numAux ;\n" +
                "    }\n", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser parser = new MethodDefParser();
        final List<Token> input = lexer.run();
        final MatchResult result = parser.parse(input);
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
        } else {
            Assert.assertEquals(input.size(), result.next().size());
        }
    }
}
