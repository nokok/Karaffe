package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.Select;

public class PathParserTest {

    @Test
    public void simple() {
        final MatchResult result = this.runTest("p", true);
        final Select selector = (Select) result.getNode().orElseThrow(AssertionError::new);
        final String path = selector.toString("/");
        Assert.assertEquals("p", path);
    }

    @Test
    public void path1() {
        final MatchResult result = this.runTest("pkg.i", true);
        final Select selector = (Select) result.getNode().orElseThrow(AssertionError::new);
        final String path = selector.toString("/");
        Assert.assertEquals("pkg/i", path);
    }

    @Test
    public void path2() {
        final MatchResult result = this.runTest("pkg.i.foo", true);
        final Select selector = (Select) result.getNode().orElseThrow(AssertionError::new);
        final String path = selector.toString("/");
        Assert.assertEquals("pkg/i/foo", path);
    }

    @Test
    public void path3() {
        final MatchResult result = this.runTest("a . b. \t b ", true);
        final Select selector = (Select) result.getNode().orElseThrow(AssertionError::new);
        final String path = selector.toString("/");
        Assert.assertEquals("a/b/b", path);
    }

    private MatchResult runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser parser = new PathParser();
        final MatchResult result = parser.parse(lexer.run());
        Assert.assertEquals(source + " " + result, v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return result;
            }
            final MatchResult eofResult = new EOFParser().match(result.next());
            if (eofResult.isFailure()) {
                Assert.fail(eofResult.toString());
            }
            Assert.assertEquals(0, eofResult.next().size());
        }
        return result;
    }
}
