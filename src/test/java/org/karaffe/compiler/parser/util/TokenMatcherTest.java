package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.KeywordToken.Class;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.IdentifierParser;
import org.karaffe.compiler.parser.Parser;

public class TokenMatcherTest {

    private final TokenMatcher failFirstTokenMatcher = input -> new MatchResult.Failure(input.get(0), input);
    private final TokenMatcher successIdMatcher = input -> new MatchResult.Success(new Tokens(input.subList(1, input.size())), new ArrayList<>(Arrays.asList(new IdentifierToken.TypeName("A"))));
    private final TokenMatcher longSuccessIdIdMatcher = input -> new MatchResult.Success(new Tokens(input.subList(2, input.size())), new ArrayList<>(Arrays.asList(new IdentifierToken.TypeName("A"), new IdentifierToken.TypeName("A"))));

    @Test
    public void testMatcher() {
        final TokenMatcher identifierMatcher1 = TokenMatcher.create(Class.class, IdentifierToken.class);
        final TokenMatcher identifierMatcher2 = TokenMatcher.concat(TokenMatcher.create(Class.class), new IdentifierParser());

        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.Class());
        input.add(new IdentifierToken.VarName("A"));

        final MatchResult result1 = identifierMatcher1.match(input);
        Assert.assertTrue(result1.isSuccess());
        final List<Token> matched1 = result1.matchedF();
        Assert.assertTrue(matched1.get(0).is(Class.class));
        Assert.assertTrue(matched1.get(1).is(IdentifierToken.class));
        Assert.assertEquals(0, result1.next().size());

        final MatchResult result2 = identifierMatcher2.match(input);
        Assert.assertTrue(result2.isSuccess());
        final List<Token> matched2 = result1.matchedF();
        Assert.assertTrue(matched2.get(0).is(Class.class));
        Assert.assertTrue(matched2.get(1).is(IdentifierToken.class));
        Assert.assertEquals(0, result2.next().size());
    }

    @Test
    public void testMatcher1() {
        final TokenMatcher matcher = TokenMatcher.concat(
                this.successIdMatcher,
                this.longSuccessIdIdMatcher);

        final List<Token> tokens = new Tokens();
        tokens.add(new IdentifierToken.TypeName("A"));
        tokens.add(new IdentifierToken.TypeName("A"));
        tokens.add(new IdentifierToken.TypeName("A"));

        final MatchResult result = matcher.match(tokens);
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testMatcher2() {
        final TokenMatcher matcher = TokenMatcher.select(
                this.failFirstTokenMatcher,
                TokenMatcher.concat(this.successIdMatcher),
                this.failFirstTokenMatcher);

        final List<Token> tokens = new Tokens();
        tokens.add(new IdentifierToken.TypeName("A"));

        final MatchResult result = matcher.match(tokens);
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testMatcher3() {
        final TokenMatcher matcher = TokenMatcher.select(
                this.failFirstTokenMatcher,
                TokenMatcher.select(this.successIdMatcher),
                this.failFirstTokenMatcher);

        final List<Token> tokens = new Tokens();
        tokens.add(new IdentifierToken.TypeName("A"));

        final MatchResult result = matcher.match(tokens);
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }

    @Test
    public void testMatcher5() {
        class P implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(null, input);
                }
                if (input.get(0).is(KeywordToken.Void.class)) {
                    return new MatchResult.Success(new Tokens(input.subList(1, input.size())), new ArrayList<>(Arrays.asList(input.get(0))));
                }
                return new MatchResult.Failure(input.get(0), input);
            }

        }

        final TokenMatcher matcher = TokenMatcher.concat(
                this.successIdMatcher,
                new P(),
                this.successIdMatcher,
                TokenMatcher.zeroOrMore(new P()));

        final List<Token> tokens = new Tokens();
        tokens.add(new IdentifierToken.TypeName("A"));
        tokens.add(new KeywordToken.Void());
        tokens.add(new IdentifierToken.TypeName("A"));

        final MatchResult result = matcher.match(tokens);
        Assert.assertEquals(result.toString(), true, result.isSuccess());
        Assert.assertEquals(result.toString(), 0, result.next().size());
    }

    @Test
    public void testMatcher6() {
        final TokenMatcher matcher = TokenMatcher.concat(
                TokenMatcher.classKeyword(),
                new IdentifierParser(),
                TokenMatcher.classKeyword(),
                TokenMatcher.zeroOrMore(new IdentifierParser()));

        final List<Token> tokens = new Tokens();
        tokens.add(new KeywordToken.Class());
        tokens.add(new IdentifierToken.TypeName("A"));
        tokens.add(new KeywordToken.Class());

        final MatchResult result = matcher.match(tokens);
        Assert.assertEquals(result.toString(), true, result.isSuccess());
        Assert.assertEquals(result.toString(), 0, result.next().size());

    }

    @Test
    public void testMatcherIdentifier() {
        this.runTest("a", TokenMatcher.identifier(), true);
        this.runTest("A", TokenMatcher.identifier(), true);
        this.runTest("b", TokenMatcher.identifier(), true);
        this.runTest("identifier", TokenMatcher.identifier(), true);
        this.runTest("i3", TokenMatcher.identifier(), true);
        this.runTest("String", TokenMatcher.identifier(), true);
        this.runTest("MAX", TokenMatcher.identifier(), true);
        this.runTest("isIdentifier", TokenMatcher.identifier(), true);
    }

    @Ignore
    public void testSnakeCaseIdentifier() {
        this.runTest("A_C", TokenMatcher.identifier(), false);
    }

    @Test
    public void testMatcherIntLiteral() {
        this.runTest("1", TokenMatcher.intLiteral(), true);
    }

    @Test
    public void testMatcherTrueLiteral() {
        this.runTest("true", TokenMatcher.trueLiteral(), true);
        this.runTest("t", TokenMatcher.trueLiteral(), false);
    }

    @Test
    public void testMatcherFalseLiteral() {
        this.runTest("false", TokenMatcher.falseLiteral(), true);
        this.runTest("f", TokenMatcher.trueLiteral(), false);
    }

    @Test
    public void testMatcherThisKeyword() {
        this.runTest("this", TokenMatcher.thisKeyword(), true);
    }

    @Test
    public void testMatcherNewKeyword() {
        this.runTest("new", TokenMatcher.newKeyword(), true);
    }

    @Test
    public void testIntKeyword() {
        this.runTest("int", TokenMatcher.intKeyword(), true);
    }

    private void runTest(final String source, final TokenMatcher m, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = m.match(input);
        Assert.assertEquals(v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            if (result.next().size() >= 2) {
                Assert.fail(result.next().toString());
            }
            Assert.assertTrue(result.next().get(0).is(EOF.class));
        } else {
            Assert.assertEquals(String.format("%s vs %s", input, result.next()), input.size(), result.next().size());
        }
    }
}
