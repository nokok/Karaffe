package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.KeywordToken.New;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.lexer.OperatorToken.Bang;
import org.karaffe.compiler.lexer.OperatorToken.Dot;
import org.karaffe.compiler.lexer.OperatorToken.LeftBracket;
import org.karaffe.compiler.lexer.OperatorToken.LeftParen;
import org.karaffe.compiler.lexer.OperatorToken.RightBracket;
import org.karaffe.compiler.lexer.OperatorToken.RightParen;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExprParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExprParser.class);

    @Override
    public MatchResult parse(final Tokens tokens) {
        ExprParser.LOGGER.debug("Input : {}", tokens);
        final TokenMatcher selector = TokenMatcher.select(
                new InfixOpExpr(),
                new ArrayAccess(),
                new LengthAccess(),
                new MethodInvocation(),
                new Primary());
        return selector.match(tokens);
    }

    public static class InfixOpExpr implements TokenMatcher {

        @Override
        public MatchResult match(final Tokens tokens) {
            return TokenMatcher.concat(new Primary(), TokenMatcher.create(OperatorToken.class), new ExprParser()).match(tokens);
        }

    }

    public static class ArrayAccess implements TokenMatcher {

        @Override
        public MatchResult match(final Tokens tokens) {
            return TokenMatcher.concat(new Primary(), TokenMatcher.create(LeftBracket.class), new ExprParser(), TokenMatcher.create(RightBracket.class)).match(tokens);
        }

    }

    public static class LengthAccess implements TokenMatcher {

        @Override
        public MatchResult match(final Tokens tokens) {
            return TokenMatcher.concat(new Primary(), TokenMatcher.create(Dot.class), TokenMatcher.create(KeywordToken.Length.class)).match(tokens);
        }

    }

    public static class MethodInvocation implements TokenMatcher {

        @Override
        public MatchResult match(final Tokens tokens) {
            return TokenMatcher.concat(new Primary(), TokenMatcher.create(Dot.class), TokenMatcher.create(IdentifierToken.class), TokenMatcher.create(LeftParen.class), new ExprParser(), TokenMatcher.create(RightParen.class)).match(tokens);
        }

    }

    public static class Primary implements TokenMatcher {

        private static final Logger L = LoggerFactory.getLogger(Primary.class);

        @Override
        public MatchResult match(final Tokens tokens) {
            Primary.L.debug("Input : {}", tokens);
            final TokenMatcher selector = TokenMatcher.select(
                    TokenMatcher.intLiteral(),
                    TokenMatcher.identifier(),
                    TokenMatcher.trueLiteral(),
                    TokenMatcher.falseLiteral(),
                    TokenMatcher.thisKeyword(),
                    new ArrayInitializer(),
                    new NewInstance(),
                    new NegativeExpr(),
                    new NestedExpr());
            return selector.match(tokens);
        }

        @Override
        public String toString() {
            return "Primary";
        }

        public static class ArrayInitializer implements TokenMatcher {

            @Override
            public MatchResult match(final Tokens tokens) {
                return TokenMatcher.concat(
                        TokenMatcher.newKeyword(),
                        TokenMatcher.intKeyword(),
                        TokenMatcher.leftBracket(),
                        new ExprParser(), // expr
                        TokenMatcher.rightBracket()).match(tokens);
            }

        }

        public static class NewInstance implements TokenMatcher {

            @Override
            public MatchResult match(final Tokens tokens) {
                return TokenMatcher.create(New.class, IdentifierToken.class, LeftParen.class, RightParen.class).match(tokens);
            }

        }

        public static class NegativeExpr implements TokenMatcher {

            @Override
            public MatchResult match(final Tokens tokens) {
                return TokenMatcher.concat(TokenMatcher.create(Bang.class), new ExprParser()).match(tokens);
            }

        }

        public static class NestedExpr implements TokenMatcher {

            @Override
            public MatchResult match(final Tokens tokens) {
                return TokenMatcher.concat(TokenMatcher.create(LeftParen.class), new ExprParser(), TokenMatcher.create(RightParen.class)).match(tokens);
            }

        }
    }

    @Override
    public String toString() {
        return "Expr";
    }

}
