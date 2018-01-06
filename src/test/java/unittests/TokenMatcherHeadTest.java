package unittests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.CommonToken.Dot;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.KeywordToken.Package;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.lexer.WhitespaceToken;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.SimpleTokenMatcher;
import org.karaffe.compiler.parser.util.MatchResult.Success;

public class TokenMatcherHeadTest {

    @Test
    public void testHeadManyWhitespaces() {
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(Package.class);
        final List<Token> tokens = new ArrayList<>();
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new KeywordToken.Class());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new IdentifierToken.TypeName("Hoge"));

        final MatchResult headResult = matcher.head(new Tokens(tokens));
        Assert.assertEquals(true, headResult.isSuccess());

        final Optional<Success> success = headResult.toSuccess();
        final List<Token> matchedTokens = success.map(s -> new ArrayList<>(s.matchedTokens())).orElseGet(ArrayList::new);

        final List<Token> next = new ArrayList<>(success.map(Success::next).orElse(new Tokens()));
        Assert.assertEquals(0, matchedTokens.size());
        Assert.assertTrue(next.get(0).is(KeywordToken.Class.class));
    }

    @Test
    public void testHeadWhitespaceOnly() {
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(Package.class);
        final List<Token> tokens = new ArrayList<>();
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new CommonToken.EOF());
        final MatchResult headResult = matcher.head(new Tokens(tokens));
        Assert.assertEquals(true, headResult.isFailure());
        Assert.assertArrayEquals(tokens.toArray(), headResult.next().toArray());
        Assert.assertTrue(headResult.errorHeadF().get().is(EOF.class));
    }

    @Test
    public void testHeadWhitespaceOnlyNoEOF() {
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(Package.class);
        final List<Token> tokens = new ArrayList<>();
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        final MatchResult headResult = matcher.head(new Tokens(tokens));
        Assert.assertEquals(true, headResult.isFailure());
        Assert.assertArrayEquals(tokens.toArray(), headResult.next().toArray());
        Assert.assertFalse(headResult.errorHeadF().isPresent());
    }

    @Test
    public void testHeadLast() {
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(Package.class);
        final List<Token> tokens = new ArrayList<>();
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.NewLine());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new WhitespaceToken.Space());
        tokens.add(new KeywordToken.Package());
        final MatchResult headResult = matcher.head(new Tokens(tokens));
        Assert.assertEquals(true, headResult.isSuccess());
        final List<Token> matchedTokens = new ArrayList<>(headResult.matchedF());
        Assert.assertEquals(0, matchedTokens.size());
    }

    @Test
    public void testNoHeadTokenFound1() {
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(Dot.class);
        final List<Token> tokens = new ArrayList<>();
        tokens.add(new Dot());
        final MatchResult head = matcher.head(new Tokens(tokens));
        Assert.assertEquals(true, head.isSuccess());
        Assert.assertEquals(1, head.next().size());
        Assert.assertEquals(Dot.class, new ArrayList<>(head.next()).get(0).getClass());
    }

    @Test
    public void testNoHeadTokenFound2() {
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(Dot.class);
        final List<Token> tokens = new ArrayList<>();
        tokens.add(new Dot());
        tokens.add(new IdentifierToken.TypeName("A"));
        final MatchResult head = matcher.head(new Tokens(tokens));
        Assert.assertEquals(true, head.isSuccess());
        Assert.assertEquals(2, head.next().size());
    }

}
