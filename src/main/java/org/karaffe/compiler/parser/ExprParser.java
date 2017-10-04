package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.CommonToken.Comma;
import org.karaffe.compiler.lexer.CommonToken.Dot;
import org.karaffe.compiler.lexer.CommonToken.LeftBracket;
import org.karaffe.compiler.lexer.CommonToken.LeftParen;
import org.karaffe.compiler.lexer.CommonToken.RightBracket;
import org.karaffe.compiler.lexer.CommonToken.RightParen;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.KeywordToken.False;
import org.karaffe.compiler.lexer.KeywordToken.Int;
import org.karaffe.compiler.lexer.KeywordToken.This;
import org.karaffe.compiler.lexer.KeywordToken.True;
import org.karaffe.compiler.lexer.LiteralToken.IntLiteral;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.lexer.OperatorToken.Bang;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.New;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Node;
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
        final MatchResult i = new InfixOpExpr().match(tokens);
        final MatchResult a = new ArrayAccess().match(tokens);
        final MatchResult l = new LengthAccess().match(tokens);
        final MatchResult m = new MethodInvocation().match(tokens);
        final MatchResult p = new Primary().match(tokens);
        if (parser.testNext(new InfixOpExpr())) {
            return parser.toSuccess();
        }
        if (parser.testNext(new ArrayAccess())) {
            return parser.toSuccess();
        }
        if (parser.testNext(new LengthAccess())) {
            return parser.toSuccess();
        }
        if (parser.testNext(new MethodInvocation())) {
            return parser.toSuccess();
        }
        if (parser.testNext(new Primary())) {
            return parser.toSuccess();
        }
        return parser.toFailure();
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
            if (result.matchedF().size() == 1) {
                final Token token = result.matchedF().get(0);
                if (!token.is(OperatorToken.class)) {
                    return new MatchResult.Failure(input);
                }
                final OperatorToken operatorToken = (OperatorToken) token;
                final String opStr = operatorToken.getText();
                return new MatchResult.Success(result.next(), result.matchedF(), new Select(new Name(opStr)));
            }
            final String operatorString = result.matchedF().stream().map(Token::getText).reduce((lt, rt) -> lt + rt).get();
            return new MatchResult.Success(result.next(), result.matchedF(), new Select(new Name(new IdentifierToken.VarName(operatorString, result.matchedF().get(0).getPosition()))));
        }

    }

    public static class InfixOpExpr implements Parser {

        private static final Logger l = LoggerFactory.getLogger(InfixOpExpr.class);

        @Override
        public MatchResult parse(final Tokens tokens) {
            if (tokens.isEmpty()) {
                return new MatchResult.Failure(tokens);
            }
            InfixOpExpr.l.debug("Input : {}", tokens);
            final ChainParser parser = new ChainParser(tokens);
            if (!parser.testNext(new Primary())) {
                return parser.toFailure();
            }
            final Node leftExpr = parser.lastMatch();
            if (!parser.testNext(new OperatorParser())) {
                return parser.toFailure();
            }
            final Node operator = parser.lastMatch();
            if (!parser.testNext(new ExprParser())) {
                return parser.toFailure();
            }
            final Node rightExpr = parser.lastMatch();

            // leftExpr.operator(rightExpr)
            return new MatchResult.Success(parser.next(), parser.matched(), new Apply(new Select(leftExpr, operator), rightExpr));
        }

    }

    public static class ArrayAccess implements Parser {

        private static final Logger L = LoggerFactory.getLogger(ArrayAccess.class);

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            ArrayAccess.L.debug("Input : {}", input);
            final ChainParser cp = new ChainParser(input);
            if (!cp.testNext(new Primary())) {
                return cp.toFailure();
            }
            final Node arrayNode = cp.lastMatch();
            if (!cp.testNext(TokenMatcher.create(LeftBracket.class))) {
                return cp.toFailure();
            }
            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node accessTarget = cp.lastMatch();
            if (!cp.testNext(TokenMatcher.create(RightBracket.class))) {
                return cp.toFailure();
            }
            // array.apply(expr)
            final Apply apply = new Apply(new Select(arrayNode, new Name("apply")), accessTarget);
            return new MatchResult.Success(cp.next(), cp.matched(), apply);
        }

    }

    public static class LengthAccess implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser cp = new ChainParser(input);
            if (!cp.testNext(new Primary())) {
                return cp.toFailure();
            }
            final Node objNode = cp.lastMatch();
            if (!cp.testNext(TokenMatcher.create(Dot.class))) {
                return cp.toFailure();
            }
            if (!cp.testNext(TokenMatcher.create(KeywordToken.Length.class))) {
                return cp.toFailure();
            }
            // obj.length
            final Apply apply = new Apply(new Select(objNode, new Name("length")));
            return new MatchResult.Success(cp.next(), cp.matched(), apply);
        }

    }

    public static class MethodInvocation implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser cp = new ChainParser(input);
            if (!cp.testNext(new Primary())) {
                return cp.toFailure();
            }
            final Node obj = cp.lastMatch();
            if (!cp.testNext(TokenMatcher.create(Dot.class))) {
                return cp.toFailure();
            }
            if (!cp.testNext(new IdentifierParser())) {
                return cp.toFailure();
            }
            final Node name = cp.lastMatch();
            if (!cp.testNext(TokenMatcher.create(LeftParen.class))) {
                return cp.toFailure();
            }

            final List<Node> arguments = new ArrayList<>();
            final boolean hasFirstArg = cp.testNext(new ExprParser());

            if (hasFirstArg) {
                arguments.add(cp.lastMatch());
            }

            while (cp.testNext(TokenMatcher.create(Comma.class)) && cp.testNext(new ExprParser())) {
                arguments.add(cp.lastMatch());
            }

            if (!cp.testNext(TokenMatcher.create(RightParen.class))) {
                return cp.toFailure();
            }
            // obj.name(expr);
            final Apply apply = new Apply(new Apply(new Select(obj, name), arguments));
            return new MatchResult.Success(cp.next(), cp.matched(), apply);
        }

    }

    public static class Primary implements TokenMatcher {

        private static final Logger L = LoggerFactory.getLogger(Primary.class);

        @Override
        public MatchResult match(final Tokens tokens) {
            if (tokens.isEmpty()) {
                return new MatchResult.Failure(tokens);
            }
            Primary.L.debug("Input : {}", tokens);
            final ChainParser parser = new ChainParser(tokens);
            if (parser.testNext(new IntLiteralParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new ThisParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new TrueLiteralParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new FalseLiteralParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new ArrayInitializer())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new NewInstance())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new NegativeExpr())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new IdentifierParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new NestedExpr())) {
                return parser.toSuccess();
            }

            return parser.toFailure();
        }

        @Override
        public String toString() {
            return "Primary";
        }

        public static class IntLiteralParser implements Parser {
            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final MatchResult result = TokenMatcher.create(IntLiteral.class).match(input);
                if (result.isFailure()) {
                    return result;
                }
                return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().get(0)));
            }
        }

        public static class ThisParser implements Parser {
            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final MatchResult result = TokenMatcher.create(This.class).match(input);
                if (result.isFailure()) {
                    return result;
                }
                return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().get(0)));
            }
        }

        public static class TrueLiteralParser implements Parser {
            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final MatchResult result = TokenMatcher.create(True.class).match(input);
                if (result.isFailure()) {
                    return result;
                }
                return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().get(0)));
            }
        }

        public static class FalseLiteralParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final MatchResult result = TokenMatcher.create(False.class).match(input);
                if (result.isFailure()) {
                    return result;
                }
                return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().get(0)));
            }

        }

        public static class ArrayInitializer implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser parser = new ChainParser(input);
                if (!parser.testNext(TokenMatcher.create(KeywordToken.New.class))) {
                    return parser.toFailure();
                }
                if (!parser.testNext(TokenMatcher.create(Int.class))) {
                    return parser.toFailure();
                }
                if (!parser.testNext(TokenMatcher.create(LeftBracket.class))) {
                    return parser.toFailure();
                }
                if (!parser.testNext(new ExprParser())) {
                    return parser.toFailure();
                }
                final Node arrayLength = parser.lastMatch();

                if (!parser.testNext(TokenMatcher.create(RightBracket.class))) {
                    return parser.toFailure();
                }
                // IntArray.<init>(expr)
                final Apply apply = new Apply(
                        new New(new Select(
                                new Name("IntArray"))),
                        arrayLength);
                return new MatchResult.Success(parser.next(), parser.matched(), apply);
            }

        }

        public static class NewInstance implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser parser = new ChainParser(input);
                if (!parser.testNext(TokenMatcher.create(KeywordToken.New.class))) {
                    return parser.toFailure();
                }

                if (!parser.testNext(new IdentifierParser())) {
                    return parser.toFailure();
                }
                final Node identifier = parser.lastMatch();
                if (!parser.testNext(TokenMatcher.create(LeftParen.class))) {
                    return parser.toFailure();
                }
                if (!parser.testNext(TokenMatcher.create(RightParen.class))) {
                    return parser.toFailure();
                }

                // id.<init>()
                final Apply n = new Apply(new New(new Select(identifier)));
                return new MatchResult.Success(parser.next(), parser.matched(), n);
            }

        }

        public static class NegativeExpr implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser parser = new ChainParser(input);
                if (!parser.testNext(TokenMatcher.create(Bang.class))) {
                    return parser.toFailure();
                }
                if (!parser.testNext(new ExprParser())) {
                    return parser.toFailure();
                }
                final Node exprNode = parser.lastMatch();
                return new MatchResult.Success(parser.next(), parser.matched(), new Apply(new Select(exprNode, new Name("minus"))));
            }

        }

        public static class NestedExpr implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser parser = new ChainParser(input);
                if (!parser.testNext(TokenMatcher.create(LeftParen.class))) {
                    return parser.toFailure();
                }
                if (!parser.testNext(new ExprParser())) {
                    return parser.toFailure();
                }
                final Node exprNode = parser.lastMatch();
                if (!parser.testNext(TokenMatcher.create(RightParen.class))) {
                    return parser.toFailure();
                }
                final Apply apply = new Apply(exprNode);
                return new MatchResult.Success(parser.next(), parser.matched(), apply);
            }

        }
    }

}
