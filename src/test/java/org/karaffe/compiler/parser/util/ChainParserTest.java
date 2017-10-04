package org.karaffe.compiler.parser.util;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.tree.Name;

public class ChainParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testParserNew() {
        this.mkParser("");
    }

    @Test
    public void testDefaultVal() {
        final ChainParser parser = this.mkParser("package");
        Assert.assertFalse(parser.hasError());
    }

    @Test
    public void testNextMatch() {
        final ChainParser parser = this.mkParser("package a.b.c");
        Assert.assertTrue(parser.nextMatch(TokenMatcher.packageKeyword()));
        Assert.assertNotNull(parser.lastMatch());
        Assert.assertEquals(7, parser.next().size());
    }

    @Test
    public void testNextMatchFail() {
        final ChainParser parser = this.mkParser("package a.b.c");
        Assert.assertFalse(parser.nextMatch(TokenMatcher.identifier()));
    }

    @Test(expected = IllegalStateException.class)
    public void testNextMatchFailWithLastMatch() {
        final ChainParser parser = this.mkParser("package a.b.c");
        Assert.assertFalse(parser.nextMatch(TokenMatcher.identifier()));
        Assert.assertFalse(parser.hasLastMatch());
        parser.lastMatch(); // throw
    }

    @Test
    public void testNextClass() {
        final ChainParser parser = this.mkParser("id");
        Assert.assertTrue(parser.nextMatch(TokenMatcher.identifier()));
        final Name n = parser.lastMatch();
        Assert.assertEquals("id", n.getText());
    }

    private ChainParser mkParser(final String source) {
        return new ChainParser(new Tokens(new KaraffeLexer(source).run()));
    }
}