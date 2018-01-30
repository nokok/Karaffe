package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.StatementParser.StmtBlock;
import org.karaffe.compiler.parser.util.MatchResult;

public class StmtBlockTest {
    @Test
    public void testEmpty() {
        this.runTest("", false);
    }

    @Test
    public void test1() {
        this.runTest("{}", true);
    }

    @Test
    public void test2() {
        this.runTest("{b=1;}", true);
    }

    @Test
    public void test3() {
        this.runTest("{b=1;a=1;}", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = new StmtBlock().parse(input);
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
