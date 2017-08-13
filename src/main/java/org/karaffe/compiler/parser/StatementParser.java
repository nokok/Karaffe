package org.karaffe.compiler.parser;

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
import org.karaffe.compiler.parser.util.AndTokenMatcher;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.SimpleTokenMatcher;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatementParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        StatementParser.LOGGER.info("Input : {}", input);
        return TokenMatcher.select(
                new StmtBlock(),
                new IfBlock(),
                new WhileBlock(),
                new SystemOutPrintln(),
                new AssignStmt(),
                new ArrayAssignStmt()).match(input);
    }

    public static class StmtBlock implements Parser {

        private static final Logger L = LoggerFactory.getLogger(StmtBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            StmtBlock.L.debug("Input :{}");
            final MatchResult result = TokenMatcher.concat(
                    TokenMatcher.create(LeftBrace.class),
                    TokenMatcher.zeroOrMore(new StatementParser()),
                    TokenMatcher.create(RightBrace.class)).match(input);
            StmtBlock.L.debug("Result : {} {}", result, input);
            return result;
        }
    }

    public static class IfBlock implements Parser {
        private static final Logger L = LoggerFactory.getLogger(IfBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            IfBlock.L.debug("Input : {}", input);
            final MatchResult result = new AndTokenMatcher(
                    new SimpleTokenMatcher(If.class),
                    new SimpleTokenMatcher(LeftParen.class),
                    new ExprParser(),
                    new SimpleTokenMatcher(RightParen.class),
                    new StatementParser(),
                    new SimpleTokenMatcher(Else.class),
                    new StatementParser()).match(input);
            IfBlock.L.debug("Result : {} {}", result, input);
            return result;
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
