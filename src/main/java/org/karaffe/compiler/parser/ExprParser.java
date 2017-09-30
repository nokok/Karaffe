package org.karaffe.compiler.parser;

import java.util.Optional;

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
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Expr;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.VarName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExprParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExprParser.class);

    @Override
    public MatchResult parse(final Tokens tokens) {
        if (tokens.isEmpty()) {
            return new MatchResult.Failure(tokens);
        }
        ExprParser.LOGGER.debug("Input : {}", tokens);
        final ChainParser parser = new ChainParser(tokens);
        boolean isPass = false;
        isPass |= parser.testNext(new InfixOpExpr(), Expr.class);
        isPass |= parser.testNext(new ArrayAccess(), Expr.class);
        isPass |= parser.testNext(new LengthAccess(), Expr.class);
        isPass |= parser.testNext(new MethodInvocation(), Expr.class);
        isPass |= parser.testNext(new Primary(), Expr.class);

        if (!isPass) {
            return parser.toFailure();
        }

        return new MatchResult.Success(parser.next(), parser.matched(), parser.lastMatch());
    }

    public static class OperatorParser implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final MatchResult result = TokenMatcher.create(OperatorToken.class).match(input);
            if (result.isFailure()) {
                return result;
            }
            return new MatchResult.Success(result.next(), result.matchedF(), new Select(new VarName(result)));
        }

    }

    public static class InfixOpExpr implements Parser {

        @Override
        public MatchResult parse(final Tokens tokens) {
            if (tokens.isEmpty()) {
                return new MatchResult.Failure(tokens);
            }
            final ChainParser parser = new ChainParser(tokens);
            final Optional<Expr> leftExpr = parser.nextMatch(new Primary(), Expr.class);
            parser.nextMatch(,)

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
