package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.ExprParser.Primary;
import org.karaffe.compiler.parser.util.MatchResult;

public class PrimaryTest {

    @Test
    public void testPrimary1() {
        final List<Token> input = new ArrayList<>();
        input.add(new LiteralToken.IntLiteral("1"));
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary2() {
        final List<Token> input = new ArrayList<>();
        input.add(new IdentifierToken.VarName("a"));
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary3() {
        final List<Token> input = new ArrayList<>();
        input.add(new LiteralToken.IntLiteral("1"));
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary4() {
        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.True());
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary5() {
        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.False());
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary6() {
        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.False());
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary7() {
        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.This());
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary8() {
        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.New());
        input.add(new KeywordToken.Int());
        input.add(new OperatorToken.LeftBracket());
        input.add(new LiteralToken.IntLiteral("1"));
        input.add(new OperatorToken.RightBracket());
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary9() {
        final List<Token> input = new ArrayList<>();
        input.add(new KeywordToken.New());
        input.add(new IdentifierToken.TypeName("A"));
        input.add(new OperatorToken.LeftParen());
        input.add(new OperatorToken.RightParen());
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary10() {
        final List<Token> input = new ArrayList<>();
        input.add(new OperatorToken.Bang());
        input.add(new IdentifierToken.VarName("a"));
        this.runSuccessTest(input);
    }

    @Test
    public void testPrimary11() {
        final List<Token> input = new ArrayList<>();
        input.add(new OperatorToken.LeftParen());
        input.add(new IdentifierToken.VarName("a"));
        input.add(new OperatorToken.RightParen());
        this.runSuccessTest(input);
    }

    private void runSuccessTest(final List<Token> input) {
        final Primary primary = new Primary();
        final MatchResult result = primary.match(input);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(0, result.next().size());
    }
}
