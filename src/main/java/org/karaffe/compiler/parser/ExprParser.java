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
import org.karaffe.compiler.lexer.OperatorToken.LessThan;
import org.karaffe.compiler.lexer.OperatorToken.Minus;
import org.karaffe.compiler.lexer.OperatorToken.Plus;
import org.karaffe.compiler.lexer.OperatorToken.Slash;
import org.karaffe.compiler.lexer.OperatorToken.Star;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.ExprParser.Primary.LiteralsParser;
import org.karaffe.compiler.parser.ExprParser.Primary.NestedExprParser;
import org.karaffe.compiler.parser.util.AbstractBinaryExprLeftAssoc;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.New;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Node;

public class ExprParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        return new CondExprParaser().parse(input);
    }

    static class CondExprParaser extends AbstractBinaryExprLeftAssoc {
        public CondExprParaser() {
            super(new PlusMinusParser(), new OperatorParser(LessThan.class), new OperatorParser(OperatorToken.AndAnd.class));
        }
    }

    static class PlusMinusParser extends AbstractBinaryExprLeftAssoc {
        public PlusMinusParser() {
            super(new MulDivExprParser(), new OperatorParser(Plus.class), new OperatorParser(Minus.class));
        }
    }

    static class MulDivExprParser extends AbstractBinaryExprLeftAssoc {
        public MulDivExprParser() {
            super(new Primary(), new OperatorParser(Star.class), new OperatorParser(Slash.class));
        }
    }

    static class OperatorParser implements Parser {

        private final Class<? extends Token> clazz;

        public OperatorParser(final Class<? extends Token> clazz) {
            this.clazz = clazz;
        }

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final MatchResult result = TokenMatcher.create(this.clazz).match(input);
            if (result.isFailure()) {
                return result;
            }
            if (result.matchedF().size() == 1) {
                final Token token = result.matchedF().get(0);
                if (!token.is(this.clazz)) {
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

    static class ExprHeadParser implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser cp = new ChainParser(input);
            if (cp.testNext(new LiteralsParser())) {
                return cp.toSuccess();
            }
            if (cp.testNext(new IdentifierParser())) {
                return cp.toSuccess();
            }
            if (cp.testNext(new NestedExprParser())) {
                return cp.toSuccess();
            }
            return cp.toFailure();
        }

    }

    static class IntLiteralParser implements Parser {
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

    static class ThisParser implements Parser {
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

    static class TrueLiteralParser implements Parser {
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

    static class FalseLiteralParser implements Parser {

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

    static class Primary implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser parser = new ChainParser(input);
            if (parser.testNext(new MethodInvocationParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new ArrayAccessParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new LiteralsParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new NewInstanceParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new NegativeExprParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new NestedExprParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new LengthAccessParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new ArrayInitializerParser())) {
                return parser.toSuccess();
            }
            if (parser.testNext(new IdentifierParser())) {
                return parser.toSuccess();
            }
            return parser.toFailure();
        }

        @Override
        public String toString() {
            return "Primary";
        }

        static class ArrayInitializerParser implements Parser {

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
                final Name arrayType = new Name("IntArray");
                final Select arrayTypeSelect = new Select(arrayType);
                final New newInstanceTarget = new New(arrayTypeSelect);
                final Apply apply = new Apply(newInstanceTarget, arrayLength);
                return new MatchResult.Success(parser.next(), parser.matched(), apply);
            }

        }

        static class NewInstanceParser implements Parser {

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

        static class LiteralsParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser parser = new ChainParser(input);
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
                return parser.toFailure();
            }

        }

        static class NegativeExprParser implements Parser {

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

        static class NestedExprParser implements Parser {

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

        static class ArrayAccessParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser cp = new ChainParser(input);
                if (!cp.testNext(new ExprHeadParser())) {
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

        static class LengthAccessParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser cp = new ChainParser(input);
                if (!cp.testNext(new ExprHeadParser())) {
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

        static class MethodInvocationParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                if (input.isEmpty()) {
                    return new MatchResult.Failure(input);
                }
                final ChainParser cp = new ChainParser(input);
                if (!cp.testNext(new ExprHeadParser())) {
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
    }
}
