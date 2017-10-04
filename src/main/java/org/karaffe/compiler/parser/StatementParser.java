package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.CommonToken.LeftBrace;
import org.karaffe.compiler.lexer.CommonToken.LeftBracket;
import org.karaffe.compiler.lexer.CommonToken.LeftParen;
import org.karaffe.compiler.lexer.CommonToken.RightBrace;
import org.karaffe.compiler.lexer.CommonToken.RightBracket;
import org.karaffe.compiler.lexer.CommonToken.RightParen;
import org.karaffe.compiler.lexer.CommonToken.Semi;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.KeywordToken.Else;
import org.karaffe.compiler.lexer.KeywordToken.If;
import org.karaffe.compiler.lexer.KeywordToken.While;
import org.karaffe.compiler.lexer.OperatorToken.Equals;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Apply;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.Goto;
import org.karaffe.compiler.tree.LabelDef;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatementParser implements Parser {

    private ChainParser parser;

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        this.parser = new ChainParser(input);
        if (this.parser.testNext(new StmtBlock())) {
            return this.parser.toSuccess();
        }
        if (this.parser.testNext(new IfBlock())) {
            return this.parser.toSuccess();
        }
        if (this.parser.testNext(new WhileBlock())) {
            return this.parser.toSuccess();
        }
        if (this.parser.testNext(new SystemOutPrintln())) {
            return this.parser.toSuccess();
        }
        if (this.parser.testNext(new AssignStmt())) {
            return this.parser.toSuccess();
        }
        if (this.parser.testNext(new ArrayAssignStmt())) {
            return this.parser.toSuccess();
        }
        return this.parser.toFailure();
    }

    public static class StmtBlock implements Parser {

        private static final Logger L = LoggerFactory.getLogger(StmtBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            StmtBlock.L.debug("Input :{}", input);
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser parser = new ChainParser(input);
            if (!parser.nextMatch(TokenMatcher.create(LeftBrace.class))) {
                return parser.toFailure();
            }

            final List<Node> statements = new ArrayList<>();
            while (parser.testNext(new StatementParser(), Node.class)) {
                statements.add(parser.lastMatch());
            }
            if (!parser.nextMatch(TokenMatcher.create(RightBrace.class))) {
                return parser.toFailure();
            }
            final Block block = new Block(statements);
            return new MatchResult.Success(parser.next(), parser.matched(), block);
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
            if (!parser.nextMatch(new ExprParser())) {
                return parser.toFailure();
            }
            final Node expr = parser.lastMatch();
            if (!parser.nextMatch(TokenMatcher.create(RightParen.class))) {
                return parser.toFailure();
            }

            if (!parser.nextMatch(new StatementParser())) {
                return parser.toFailure();
            }
            final Node stmtThen = parser.lastMatch();

            if (!parser.nextMatch(TokenMatcher.create(Else.class))) {
                return parser.toFailure();
            }
            if (!parser.nextMatch(new StatementParser())) {
                return parser.toFailure();
            }
            final Node stmtElse = parser.lastMatch();

            // if cond ? goto then : goto else
            //
            return new MatchResult.Success(parser.next(), parser.matched(), new org.karaffe.compiler.tree.If(expr, stmtThen, stmtElse));
        }

    }

    public static class WhileBlock implements Parser {
        private static final Logger L = LoggerFactory.getLogger(WhileBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            WhileBlock.L.debug("Input :{}");
            final ChainParser cp = new ChainParser(input);
            if (!cp.nextMatch(TokenMatcher.create(While.class))) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(TokenMatcher.create(LeftParen.class))) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(new ExprParser())) {
                return cp.toFailure();
            }
            final Node cond = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(RightParen.class))) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(new StatementParser())) {
                return cp.toFailure();
            }
            final Node stmt = cp.lastMatch();

            // label: a
            // if (cond) {
            // stmt
            // goto a
            // }
            final List<Node> nodes = new ArrayList<>();
            final LabelDef labelDef = new LabelDef();
            nodes.add(labelDef);
            stmt.addChild(new Goto(labelDef));
            nodes.add(new org.karaffe.compiler.tree.If(cond, stmt));
            final Block whileBlock = new Block(nodes);

            return new MatchResult.Success(cp.next(), cp.matched(), whileBlock);
        }
    }

    public static class SystemOutPrintln implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser cp = new ChainParser(input);
            if (!cp.nextMatch(TokenMatcher.create(KeywordToken.SystemOutPrintln.class))) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(TokenMatcher.create(LeftParen.class))) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(new ExprParser())) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(TokenMatcher.create(RightParen.class))) {
                return cp.toFailure();
            }
            final Node exprNode = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(Semi.class))) {
                return cp.toFailure();
            }
            // System.out.println(expr)
            return new MatchResult.Success(cp.next(), cp.matched(), new Apply(new Select(new Name("java"), new Name("lang"), new Name("System"), new Name("println")), exprNode));
        }

    }

    public static class AssignStmt implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser cp = new ChainParser(input);
            if (!cp.nextMatch(new IdentifierParser())) {
                return cp.toFailure();
            }
            final Node target = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(Equals.class))) {
                return cp.toFailure();
            }

            if (!cp.nextMatch(new ExprParser())) {
                return cp.toFailure();
            }
            final Node exprNode = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(Semi.class))) {
                return cp.toFailure();
            }

            return new MatchResult.Success(cp.next(), cp.matched(), new Assign(target, exprNode));
        }

    }

    public static class ArrayAssignStmt implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            if (input.isEmpty()) {
                return new MatchResult.Failure(input);
            }
            final ChainParser cp = new ChainParser(input);
            if (!cp.nextMatch(new IdentifierParser())) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(TokenMatcher.create(LeftBracket.class))) {
                return cp.toFailure();
            }
            if (!cp.nextMatch(new ExprParser())) {
                return cp.toFailure();
            }
            final Node arrayAccess = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(RightBracket.class))) {
                return cp.toFailure();
            }
            final Node target = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(Equals.class))) {
                return cp.toFailure();
            }

            if (!cp.nextMatch(new ExprParser())) {
                return cp.toFailure();
            }
            final Node exprNode = cp.lastMatch();
            if (!cp.nextMatch(TokenMatcher.create(Semi.class))) {
                return cp.toFailure();
            }
            target.addChild(arrayAccess);
            return new MatchResult.Success(cp.next(), cp.matched(), new Assign(target, exprNode));

        }

    }

}
