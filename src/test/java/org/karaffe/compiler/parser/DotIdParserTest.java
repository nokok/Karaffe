package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;

public class DotIdParserTest {

    @Test
    public void test1() {
        runTest(".hoge", true);
    }

    @Test
    public void test2() {
        runTest(".", false);
    }

    @Test
    public void test3() {
        runTest("foo", false);
    }

    @Test
    public void test4() {
        runTest("", false);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser parser = new DotIdParser();
        final List<Token> input = lexer.run();
        final MatchResult result = parser.parse(input);
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
        } else {
            Assert.assertEquals(input.size(), result.next().size());
        }
    }
}
