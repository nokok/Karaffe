package org.karaffe.compiler.parser.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.IdentifierToken.TypeName;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.KeywordToken.Class;
import org.karaffe.compiler.lexer.OperatorToken.Semi;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;

public class SimpleTokenMatcherTest {
    @Test
    public void testLinearToken() {
        final KaraffeLexer lexer = new KaraffeLexer("     class A {}");
        final TokenMatcher matcher = TokenMatcher.create(Class.class, TypeName.class);
        final MatchResult result = matcher.match(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        final List<Token> matchedTokens = result.matchedF();
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertTrue(matchedTokens.get(0).is(Class.class));
        Assert.assertTrue(matchedTokens.get(1).is(TypeName.class));
        final Tokens next = result.next();
        Assert.assertEquals(4, next.size()); // ' ', '{', '}', 'EOF'
    }

    @Test
    public void testFailToken() {
        final KaraffeLexer lexer = new KaraffeLexer("     class ; A {}");
        final TokenMatcher matcher = TokenMatcher.create(Class.class, TypeName.class);
        final MatchResult result = matcher.match(lexer.run());
        Assert.assertEquals(true, result.isFailure());
        final Token erroredToken = result.erroredHead().orElseThrow(IllegalStateException::new);
        Assert.assertTrue(erroredToken.is(Semi.class));
    }

    @Test
    public void testOneToken() {
        final KaraffeLexer lexer = new KaraffeLexer("A");
        final SimpleTokenMatcher matcher = new SimpleTokenMatcher(IdentifierToken.class);
        final MatchResult result = matcher.match(lexer.run());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(1, result.next().size()); // EOF
    }

}
