package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;

public class StatementParserTest {

    @Test
    public void testStmt1() {
        this.runTest("{}", true);
    }

    @Test
    public void testStmt2() {
        final StringBuilder source = new StringBuilder();
        source.append("if (true) {").append(System.lineSeparator());
        source.append("  a = 1;").append(System.lineSeparator());
        source.append("} else {").append(System.lineSeparator());
        source.append("  arr[1] = 1;").append(System.lineSeparator());
        source.append("}");
        this.runTest(source.toString(), true);
    }

    @Test
    public void testStmt3() {
        final StringBuilder source = new StringBuilder();
        source.append("while(false) {").append(System.lineSeparator());
        source.append("  a = 1;").append(System.lineSeparator());
        source.append("}");
        this.runTest(source.toString(), true);
    }

    @Test
    public void testStmt4() {
        final StringBuilder source = new StringBuilder();
        source.append("System.out.println(1);");
        this.runTest(source.toString(), true);
    }

    @Test
    public void testStmt5() {
        final StringBuilder source = new StringBuilder();
        source.append("a = 123;");
        this.runTest(source.toString(), true);
    }

    @Test
    public void testStmt6() {
        final StringBuilder source = new StringBuilder();
        source.append("arr[1 + 2] = 123;");
        this.runTest(source.toString(), true);
    }

    @Test
    public void testStmt7() {
        this.runTest("{b=1; c=2;}", true);
    }

    @Test
    public void testStmt8() {
        this.runTest("{{{}}}", true);
    }

    @Test
    public void testStmt9() {
        this.runTest("if (true) {} else {}", true);
    }

    @Test
    public void testStmt10() {
        this.runTest(")", false);
    }

    @Test
    public void testStmt11() {
        this.runTest("{b=1;}", true);
    }

    @Test
    public void testStmt12() {
        this.runTest("{b=1;c= 13;}", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = new StatementParser().match(input);
        Assert.assertEquals(v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            if (result.next().size() >= 2) {
                Assert.fail(result.next().toString());
            }
            Assert.assertTrue(result.next().get(0).is(EOF.class));
        } else {
            Assert.assertEquals(String.format("%s vs %s", input, result.next()), input.size(), result.next().size());
        }
    }

}
