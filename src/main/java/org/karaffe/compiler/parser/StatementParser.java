package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.KeywordToken.Else;
import org.karaffe.compiler.lexer.KeywordToken.If;
import org.karaffe.compiler.lexer.KeywordToken.While;
import org.karaffe.compiler.lexer.OperatorToken.Equals;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.LeftBracket;
import org.karaffe.compiler.lexer.OperatorToken.LeftParen;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightBracket;
import org.karaffe.compiler.lexer.OperatorToken.RightParen;
import org.karaffe.compiler.lexer.OperatorToken.Semi;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.Expr;
import org.karaffe.compiler.tree.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatementParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementParser.class);

    private ChainParser parser;

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        this.parser = new ChainParser(input);
        if (this.parser.testNext(new StmtBlock(), Statement.class)) {
            return new MatchResult.Success(this.parser.next(), this.parser.matched(), this.parser.lastMatch());
        }
        if (this.parser.testNext(new IfBlock(), Statement.class)) {
            return new MatchResult.Success(this.parser.next(), this.parser.matched(), this.parser.lastMatch());
        }
        if (this.parser.testNext(new WhileBlock(), Statement.class)) {
            return new MatchResult.Success(this.parser.next(), this.parser.matched(), this.parser.lastMatch());
        }
        if (this.parser.testNext(new SystemOutPrintln(), Statement.class)) {
            return new MatchResult.Success(this.parser.next(), this.parser.matched(), this.parser.lastMatch());
        }
        if (this.parser.testNext(new AssignStmt(), Statement.class)) {
            return new MatchResult.Success(this.parser.next(), this.parser.matched(), this.parser.lastMatch());
        }
        if (this.parser.testNext(new ArrayAssignStmt(), Statement.class)) {
            return new MatchResult.Success(this.parser.next(), this.parser.matched(), this.parser.lastMatch());
        }
        return this.parser.toFailure();
    }

    public static class StmtBlock implements Parser {

        private static final Logger L = LoggerFactory.getLogger(StmtBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            StmtBlock.L.debug("Input :{}");
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser parser = new ChainParser(input);
            if (!parser.nextMatch(TokenMatcher.create(LeftBrace.class))) {
                return parser.toFailure();
            }

            final List<Statement> statements = new ArrayList<>();
            while (parser.testNext(new StatementParser(), Statement.class)) {
                statements.add(parser.lastMatch());
            }
            if (!parser.nextMatch(TokenMatcher.create(RightBrace.class))) {
                return parser.toFailure();
            }

            return new MatchResult.Success(parser.next(), parser.matched(), );
        }
    }

    public static class IfBlock implements Parser {
        private static final Logger L = LoggerFactory.getLogger(IfBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            IfBlock.L.debug("Input : {}", input);
            final ChainParser parser = new ChainParser(input);
            if (!parser.nextMatch(TokenMatcher.create(If.class))) {
                return parser.toFailure();
            }
            if (!parser.nextMatch(TokenMatcher.create(LeftParen.class))) {
                return parser.toFailure();
            }
            final Optional<Expr> expr = parser.nextMatch(new ExprParser(), Expr.class);
            if (!expr.isPresent()) {
                return parser.toFailure();
            }
            if (!parser.nextMatch(TokenMatcher.create(RightParen.class))) {
                return parser.toFailure();
            }

            final Optional<Statement> stmtThen = parser.nextMatch(new StatementParser(), Statement.class);
            if (!stmtThen.isPresent()) {
                return parser.toFailure();
            }

            if (!parser.nextMatch(TokenMatcher.create(Else.class))) {
                return parser.toFailure();
            }
            final Optional<Statement> stmtElse = parser.nextMatch(new StatementParser(), Statement.class);
            if (!stmtElse.isPresent()) {
                return parser.toFailure();
            }

            return new MatchResult.Success(input, input, null);
        }

    }

    public static class WhileBlock implements Parser {
        private static final Logger L = LoggerFactory.getLogger(WhileBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            WhileBlock.L.debug("Input :{}");
            final MatchResult result = TokenMatcher.concat(
                    TokenMatcher.create(While.class),
                    TokenMatcher.create(LeftParen.class),
                    new ExprParser(),
                    TokenMatcher.create(RightParen.class),
                    new StatementParser()).match(input);
            WhileBlock.L.debug("Result : {} {}", result, input);
            return result;
        }
    }

    public static class SystemOutPrintln implements Parser {
        private static final Logger L = LoggerFactory.getLogger(SystemOutPrintln.class);

        @Override
        public MatchResult parse(final Tokens input) {
            SystemOutPrintln.L.debug("Input :{}");
            final MatchResult result = TokenMatcher.concat(
                    TokenMatcher.create(org.karaffe.compiler.lexer.KeywordToken.SystemOutPrintln.class),
                    TokenMatcher.create(LeftParen.class),
                    new ExprParser(),
                    TokenMatcher.create(RightParen.class),
                    TokenMatcher.create(Semi.class)).match(input);
            SystemOutPrintln.L.debug("Result : {} {}", result, input);
            return result;
        }

    }

    public static class AssignStmt implements Parser {
        private static final Logger L = LoggerFactory.getLogger(AssignStmt.class);

        @Override
        public MatchResult parse(final Tokens input) {
            AssignStmt.L.debug("Input :{}");
            final MatchResult result = TokenMatcher.concat(
                    TokenMatcher.identifier(),
                    TokenMatcher.create(Equals.class),
                    new ExprParser(),
                    TokenMatcher.create(Semi.class)).match(input);
            AssignStmt.L.debug("Result : {} {}", result, input);
            return result;
        }

    }

    public static class ArrayAssignStmt implements Parser {
        private static final Logger L = LoggerFactory.getLogger(ArrayAssignStmt.class);

        @Override
        public MatchResult parse(final Tokens input) {
            ArrayAssignStmt.L.debug("Input :{}");
            final MatchResult result = TokenMatcher.concat(
                    TokenMatcher.identifier(),
                    TokenMatcher.create(LeftBracket.class),
                    new ExprParser(),
                    TokenMatcher.create(RightBracket.class),
                    TokenMatcher.create(Equals.class),
                    new ExprParser(),
                    TokenMatcher.create(Semi.class)).match(input);
            ArrayAssignStmt.L.debug("Result : {} {}", result, input);
            return result;
        }

    }

}
