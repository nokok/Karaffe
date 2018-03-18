package unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.lexer.WhitespaceToken;
import org.karaffe.compiler.parser.util.TokenMatcher;

public class TokenMatcherTest {
    @Test
    public void testTokenMatcherLineEnd1() {
        assertTrue(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new CommonToken.EOF()))));
    }

    @Test
    public void testTokenMatcherLineEnd2() {
        assertTrue(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new WhitespaceToken.NewLine()))));
    }

    @Test
    public void testTokenMatcherLineEnd3() {
        assertTrue(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new WhitespaceToken.Space(), new WhitespaceToken.NewLine()))));
    }

    @Test
    public void testTokenMatcherLineEnd4() {
        assertTrue(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new WhitespaceToken.CR()))));
    }

    @Test
    public void testTokenMatcherLineEnd5() {
        assertFalse(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new IdentifierToken.VarName("A"), new WhitespaceToken.NewLine()))));
    }

    @Test
    public void testTokenMatcherLineEnd6() {
        assertTrue(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new WhitespaceToken.Space(), new CommonToken.EOF()))));
    }

    @Test
    public void testTokenMatcherLineEnd7() {
        assertTrue(TokenMatcher.isLineEnd(new Tokens(Arrays.asList(new WhitespaceToken.NewLine(), new WhitespaceToken.NewLine()))));
    }
}
