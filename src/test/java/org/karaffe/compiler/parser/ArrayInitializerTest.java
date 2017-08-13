package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.ExprParser.Primary.ArrayInitializer;
import org.karaffe.compiler.parser.util.MatchResult;

public class ArrayInitializerTest {
    @Test
    public void testArrayInitializerInt() {
        this.runTest("new int[1]", true);
    }

    @Test
    public void testArrayInitializerExpr() {
        this.runTest("new int[1+2]", true);
    }

    @Test
    public void testNewInt() {
        this.runTest("newint[1]", false);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = new ArrayInitializer().match(input);
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
