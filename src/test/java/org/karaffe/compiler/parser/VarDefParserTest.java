package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;

public class VarDefParserTest {

    @Test
    public void testVarDecl() {
        this.runTest("String a;", true);
    }

    @Test
    public void test2() {
        this.runTest("int a;", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser varDeclParser = new VarDefParser();
        final MatchResult result = varDeclParser.parse(lexer.run());
        Assert.assertEquals(result.toString(), v, result.isSuccess());
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
