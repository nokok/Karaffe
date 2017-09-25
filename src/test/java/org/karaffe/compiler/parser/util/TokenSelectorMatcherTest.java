package org.karaffe.compiler.parser.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.KeywordToken.Class;
import org.karaffe.compiler.lexer.KeywordToken.Package;
import org.karaffe.compiler.lexer.OperatorToken.LeftBracket;
import org.karaffe.compiler.lexer.OperatorToken.RightBracket;
import org.karaffe.compiler.lexer.Token;

public class TokenSelectorMatcherTest {

    @Test
    public void testSelector() {
        final TokenMatcher matcher = new OrTokenMatcher(TokenMatcher.create(IdentifierToken.class), TokenMatcher.create(Package.class));
        final KaraffeLexer lexer = new KaraffeLexer("package");
        final MatchResult result = matcher.match(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        final List<Token> matchedToken = result.matchedF();
        Assert.assertEquals(1, matchedToken.size());
        Assert.assertTrue(matchedToken.get(0).is(Package.class));
    }

    @Test
    public void testGreedy() {
        final TokenMatcher tokenMatcher = new OrTokenMatcher(
                TokenMatcher.create(Class.class, IdentifierToken.class),
                TokenMatcher.create(Class.class, IdentifierToken.class, LeftBracket.class, IdentifierToken.class, RightBracket.class)); // < -- Select
        final KaraffeLexer lexer = new KaraffeLexer("class A[A]");
        final MatchResult result = tokenMatcher.match(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        final List<Token> matchedToken = result.matchedF();
        Assert.assertEquals(5, matchedToken.size()); // Class Identifier [ Identifier ]
        Assert.assertTrue(matchedToken.get(0).is(Class.class));
        Assert.assertTrue(matchedToken.get(1).is(IdentifierToken.class));
        Assert.assertTrue(matchedToken.get(2).is(LeftBracket.class));
        Assert.assertTrue(matchedToken.get(3).is(IdentifierToken.class));
        Assert.assertTrue(matchedToken.get(4).is(RightBracket.class));
        Assert.assertEquals(1, result.next().size()); // EOF
    }

    @Test
    public void testGreedyMiss() {
        final TokenMatcher tokenMatcher = new OrTokenMatcher(
                TokenMatcher.create(Class.class, IdentifierToken.class), // < -- Select
                TokenMatcher.create(Class.class, IdentifierToken.class, LeftBracket.class, IdentifierToken.class, RightBracket.class));
        final KaraffeLexer lexer = new KaraffeLexer("class A[]");
        final MatchResult result = tokenMatcher.match(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        final List<Token> matchedToken = result.matchedF();
        Assert.assertEquals(2, matchedToken.size()); // Class Identifier
        Assert.assertTrue(matchedToken.get(0).is(Class.class));
        Assert.assertTrue(matchedToken.get(1).is(IdentifierToken.class));
        Assert.assertEquals(3, result.next().size()); // [ ] EOF
    }

}
