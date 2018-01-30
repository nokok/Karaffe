package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.StatementParser.IfBlock;
import org.karaffe.compiler.parser.util.MatchResult;

public class IfBlockTest {
    @Test
    public void testEmpty() {
        this.runTest("", false);
    }

    @Test
    public void test1() {
        this.runTest("if(true){}else{}", true);
    }

    @Test
    public void testExpr() {
        this.runTest("if(!false){}else{}", true);
    }

    @Test
    public void testStmt1() {
        this.runTest("if(!false){\n"
                + "a = 1;"
                + "}else{}", true);
    }

    @Test
    public void testStmt2() {
        this.runTest("if(!false){\n"
                + "a = 1;"
                + "}else{\n"
                + "a = 1;"
                + "}", true);
    }

    @Test
    public void testIf() {
        this.runTest("        if (num < 1) {\n" +
                "            numAux = 1 ;\n" +
                "        } else {\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                "        }", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = new IfBlock().parse(input);
        Assert.assertEquals(v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            if (result.next().size() >= 2) {
                Assert.fail(result.next().toString());
            }
            Assert.assertTrue(new ArrayList<>(result.next()).get(0).is(EOF.class));
        } else {
            Assert.assertEquals(String.format("%s vs %s", input, result.next()), input.size(), result.next().size());
        }
    }
}
