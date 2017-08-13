package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.StatementParser.WhileBlock;
import org.karaffe.compiler.parser.util.MatchResult;

public class WhileBlockTest {

    @Test
    public void testWhileExpr() {
        this.runTest("while(true){\n"
                + "}", true);
    }

    @Test
    public void testWhileExpr2() {
        this.runTest("while(!true){\n"
                + "}", true);
    }

    @Test
    public void testWhileStmt() {
        this.runTest("while(!true){\n"
                + "a = new int[1];"
                + "}", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = new WhileBlock().match(input);
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
