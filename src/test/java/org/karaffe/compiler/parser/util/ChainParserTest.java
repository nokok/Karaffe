package org.karaffe.compiler.parser.util;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.KeywordToken.Package;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.tree.Name;

public class ChainParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testParserNew() {
        this.mkParser("");
    }

    @Test
    public void testDefaultVal() {
        final CParser parser = this.mkParser("package");
        Assert.assertFalse(parser.hasError());
    }

    @Test
    public void testtestNext() {
        final CParser parser = this.mkParser("package a.b.c");
        Assert.assertTrue(parser.testNext(Package.class));
        Assert.assertNotNull(parser.lastMatch());
        Assert.assertEquals(7, parser.next().size());
    }

    @Test
    public void testtestNextFail() {
        final CParser parser = this.mkParser("package a.b.c");
        Assert.assertFalse(parser.testNext(TokenMatcher.identifier()));
    }

    @Test(expected = IllegalStateException.class)
    public void testtestNextFailWithLastMatch() {
        final CParser parser = this.mkParser("package a.b.c");
        Assert.assertFalse(parser.testNext(TokenMatcher.identifier()));
        Assert.assertFalse(parser.hasLastMatch());
        parser.lastMatch(); // throw
    }

    @Test
    public void testNextClass() {
        final CParser parser = this.mkParser("id");
        Assert.assertTrue(parser.testNext(TokenMatcher.identifier()));
        final Name n = parser.lastMatch();
        Assert.assertEquals("id", n.getText());
    }

    private CParser mkParser(final String source) {
        return new CParser(new Tokens(new KaraffeLexer(source).run()));
    }
}
