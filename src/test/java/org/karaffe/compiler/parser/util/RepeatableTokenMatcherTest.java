package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.OperatorToken.Dot;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult.Success;

public class RepeatableTokenMatcherTest {

    @Test
    public void testDotRepeatable() {
        final TokenMatcher matcher = TokenMatcher.dot();
        final ManyTokenMatcher repMatcher = new ManyTokenMatcher(matcher);
        final List<Token> checkTokens = new ArrayList<>();

        checkTokens.add(new Dot());
        MatchResult result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        List<Token> matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(1, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(3, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(4, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new IdentifierToken.VarName("a"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(4, matchedTokens.size());
        Assert.assertEquals(1, result.next().size());
        Assert.assertTrue(result.next().get(0).is(IdentifierToken.VarName.class));

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(4, matchedTokens.size());
        Assert.assertEquals(2, result.next().size());
        Assert.assertTrue(result.next().get(0).is(IdentifierToken.VarName.class));
        Assert.assertTrue(result.next().get(1).is(Dot.class));

    }

    @Test
    public void testDotIdentifierRepeatable() {
        final TokenMatcher repMatcher = TokenMatcher.manyDotIdentifier();
        final List<Token> checkTokens = new ArrayList<>();

        checkTokens.add(new Dot());
        checkTokens.add(new IdentifierToken.TypeName("A"));
        MatchResult result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        List<Token> matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertTrue(matchedTokens.get(0).is(Dot.class));
        Assert.assertTrue(matchedTokens.get(1).is(IdentifierToken.TypeName.class));
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        checkTokens.add(new IdentifierToken.TypeName("B"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(4, matchedTokens.size());
        Assert.assertTrue(matchedTokens.get(0).is(Dot.class));
        Assert.assertTrue(matchedTokens.get(1).is(IdentifierToken.TypeName.class));
        Assert.assertTrue(matchedTokens.get(2).is(Dot.class));
        Assert.assertTrue(matchedTokens.get(3).is(IdentifierToken.TypeName.class));
        Assert.assertEquals(0, result.next().size());

    }

    @Test
    public void testDotLimitedRepeatable() {
        final TokenMatcher matcher = TokenMatcher.dot();
        final ManyTokenMatcher repMatcher = new ManyTokenMatcher(matcher, 2);
        final List<Token> checkTokens = new ArrayList<>();

        checkTokens.add(new Dot());
        MatchResult result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        List<Token> matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(1, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(1, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(2, result.next().size());

        checkTokens.add(new IdentifierToken.VarName("a"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(3, result.next().size());
        Assert.assertTrue(result.next().get(2).is(IdentifierToken.VarName.class));

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(4, result.next().size());
        Assert.assertTrue(result.next().get(2).is(IdentifierToken.VarName.class));
        Assert.assertTrue(result.next().get(3).is(Dot.class));

    }

    @Test
    public void testDotLimitedRepeatable5() {
        final TokenMatcher matcher = TokenMatcher.dot();
        final ManyTokenMatcher repMatcher = new ManyTokenMatcher(matcher, 5);
        final List<Token> checkTokens = new ArrayList<>();

        checkTokens.add(new Dot());
        MatchResult result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        List<Token> matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(1, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new IdentifierToken.VarName("a"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(1, result.next().size());

        checkTokens.add(new IdentifierToken.VarName("a"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(2, result.next().size());

        checkTokens.add(new IdentifierToken.VarName("a"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(3, result.next().size());

        checkTokens.add(new IdentifierToken.VarName("a"));
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(4, result.next().size());
    }

    @Test
    public void testDotLimitedRepeatable2() {
        final TokenMatcher matcher = TokenMatcher.dot();
        final ManyTokenMatcher repMatcher = new ManyTokenMatcher(matcher, 2);
        final List<Token> checkTokens = new ArrayList<>();

        checkTokens.add(new Dot());
        MatchResult result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        List<Token> matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(1, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(0, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(1, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isSuccess());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(2, matchedTokens.size());
        Assert.assertEquals(2, result.next().size());

    }

    @Test
    public void testRepeatableMatchFail() {
        final TokenMatcher matcher = TokenMatcher.dot();
        final ManyTokenMatcher repMatcher = new ManyTokenMatcher(matcher, 2);
        final List<Token> checkTokens = new ArrayList<>();

        checkTokens.add(new IdentifierToken.VarName("A"));
        MatchResult result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isFailure());
        List<Token> matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(0, matchedTokens.size());
        Assert.assertEquals(1, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isFailure());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(0, matchedTokens.size());
        Assert.assertEquals(2, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isFailure());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(0, matchedTokens.size());
        Assert.assertEquals(3, result.next().size());

        checkTokens.add(new Dot());
        result = repMatcher.match(checkTokens);
        Assert.assertTrue(result.isFailure());
        matchedTokens = result.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
        Assert.assertEquals(0, matchedTokens.size());
        Assert.assertEquals(4, result.next().size());

    }

}
