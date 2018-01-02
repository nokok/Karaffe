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
import org.karaffe.compiler.lexer.KeywordToken.Length;
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
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.CParser.Action;
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
            final MatchResult result = TokenMatcher.create(this.clazz).match(input);
            if (result.isFailure()) {
                return result;
            }
            if (result.matchedF().size() == 1) {
                final Token token = result.matchedF().iterator().next();
                if (!token.is(this.clazz)) {
                    return new MatchResult.Failure(input);
                }
                final OperatorToken operatorToken = (OperatorToken) token;
                final String opStr = operatorToken.getText();
                return new MatchResult.Success(result.next(), result.matchedF(), new Select(new Name(opStr)));
            }
            final String operatorString = result.matchedF().stream().map(Token::getText).reduce((lt, rt) -> lt + rt).get();
            return new MatchResult.Success(result.next(), result.matchedF(), new Select(new Name(new IdentifierToken.VarName(operatorString, result.matchedF().iterator().next().getPosition()))));
        }

    }

    static class ExprHeadParser implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            final CParser cp = new CParser(input);
            if (cp.selectOne(new LiteralsParser(), new IdentifierParser(), new NestedExprParser())) {
                return cp.toSuccess();
            }
            return cp.toFailure();
        }
    }

    static class IntLiteralParser implements Parser {
        @Override
        public MatchResult parse(final Tokens input) {
            final MatchResult result = TokenMatcher.create(IntLiteral.class).match(input);
            if (result.isFailure()) {
                return result;
            }
            return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().iterator().next()));
        }
    }

    static class ThisParser implements Parser {
        @Override
        public MatchResult parse(final Tokens input) {
            final MatchResult result = TokenMatcher.create(This.class).match(input);
            if (result.isFailure()) {
                return result;
            }
            return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().iterator().next()));
        }
    }

    static class TrueLiteralParser implements Parser {
        @Override
        public MatchResult parse(final Tokens input) {
            final MatchResult result = TokenMatcher.create(True.class).match(input);
            if (result.isFailure()) {
                return result;
            }
            return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().iterator().next()));
        }
    }

    static class FalseLiteralParser implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            final MatchResult result = TokenMatcher.create(False.class).match(input);
            if (result.isFailure()) {
                return result;
            }
            return new MatchResult.Success(result.next(), result.matchedF(), new Literal(result.matchedF().iterator().next()));
        }

    }

    static class Primary implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            final CParser cp = new CParser(input);
            if (cp.selectFirst(
                    new MethodInvocationParser(),
                    new ArrayAccessParser(),
                    new LiteralsParser(),
                    new NewInstanceParser(),
                    new NegativeExprParser(),
                    new NestedExprParser(),
                    new LengthAccessParser(),
                    new ArrayInitializerParser(),
                    new PathParser())) {
                return cp.toSuccess();
            }
            return cp.toFailure();
        }

        @Override
        public String toString() {
            return "Primary";
        }

        static class ArrayInitializerParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                return cp.chain(nodes -> {
                    Node arrayLength = nodes.get(3);
                    final Name arrayType = new Name("IntArray");
                    final Select arrayTypeSelect = new Select(arrayType);
                    final New newInstanceTarget = new New(arrayTypeSelect);
                    final Apply apply = new Apply(newInstanceTarget, arrayLength);
                    return apply;
                },
                        Action.of(KeywordToken.New.class),
                        Action.of(Int.class),
                        Action.of(LeftBracket.class),
                        Action.of(new ExprParser()),
                        Action.of(RightBracket.class));
            }

        }

        static class NewInstanceParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                return cp.chain(nodes -> {
                    Node identifier = nodes.get(1);
                    return new Apply(new New(new Select(identifier)));
                },
                        Action.of(KeywordToken.New.class),
                        Action.of(new IdentifierParser()),
                        Action.of(LeftParen.class),
                        Action.of(RightParen.class));

            }

        }

        static class LiteralsParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                if (cp.selectFirst(
                        new IntLiteralParser(),
                        new ThisParser(),
                        new TrueLiteralParser(),
                        new FalseLiteralParser())) {
                    return cp.toSuccess();
                }
                return cp.toFailure();
            }

        }

        static class NegativeExprParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                return cp.chain(nodes -> {
                    Node exprNode = nodes.get(1);
                    // negate(expr)
                    return new Apply(new Select(new Name("negate")), exprNode);
                },
                        Action.of(Bang.class),
                        Action.of(new ExprParser()));
            }

        }

        static class NestedExprParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                return cp.chain(nodes -> {
                    Node exprNode = nodes.get(1);
                    return new Apply(exprNode);
                },
                        Action.of(LeftParen.class),
                        Action.of(new ExprParser()),
                        Action.of(RightParen.class));
            }

        }

        static class ArrayAccessParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                return cp.chain(nodes -> {
                    Node arrayNode = nodes.get(0);
                    Node accessTarget = nodes.get(2);
                    // array.apply(expr)
                    return new Apply(new Select(arrayNode, new Name("apply")), accessTarget);
                },
                        Action.of(new ExprHeadParser()),
                        Action.of(LeftBracket.class),
                        Action.of(new ExprParser()),
                        Action.of(RightBracket.class));
            }

        }

        static class LengthAccessParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);
                return cp.chain(nodes -> {
                    Node objNode = nodes.get(0);
                    // obj.length
                    return new Apply(new Select(objNode, new Name("length")));
                },
                        Action.of(new ExprHeadParser()),
                        Action.of(Dot.class),
                        Action.of(Length.class));
            }

        }

        static class MethodInvocationParser implements Parser {

            @Override
            public MatchResult parse(final Tokens input) {
                final CParser cp = new CParser(input);

                if (!cp.testNext(new ExprHeadParser())) {
                    return cp.toFailure();
                }
                final Node obj = cp.lastMatch();
                if (!cp.testNext(Dot.class)) {
                    return cp.toFailure();
                }
                if (!cp.testNext(new IdentifierParser())) {
                    return cp.toFailure();
                }
                final Node name = cp.lastMatch();
                if (!cp.testNext(LeftParen.class)) {
                    return cp.toFailure();
                }

                final List<Node> arguments = new ArrayList<>();
                final boolean hasFirstArg = cp.testNext(new ExprParser());

                if (hasFirstArg) {
                    arguments.add(cp.lastMatch());
                }

                while (cp.testNext(Comma.class) && cp.testNext(new ExprParser())) {
                    arguments.add(cp.lastMatch());
                }

                if (!cp.testNext(RightParen.class)) {
                    return cp.toFailure();
                }
                // obj.name(expr);
                final Apply apply = new Apply(new Apply(new Select(obj, name), arguments));
                return new MatchResult.Success(cp.next(), cp.matched(), apply);
            }

        }
    }
}
