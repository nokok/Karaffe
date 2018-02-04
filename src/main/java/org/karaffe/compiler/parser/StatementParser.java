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
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
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

    public static class ArrayAssignStmt implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            final CParser cp = new CParser(input);
            if (!cp.testNext(new IdentifierParser())) {
                return cp.toFailure();
            }
            if (!cp.testNext(LeftBracket.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node arrayAccess = cp.lastMatch();
            if (!cp.testNext(RightBracket.class)) {
                return cp.toFailure();
            }
            final Node target = cp.lastMatch();
            if (!cp.testNext(Equals.class)) {
                return cp.toFailure();
            }

            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node exprNode = cp.lastMatch();
            if (!cp.testNext(Semi.class)) {
                return cp.toFailure();
            }
            target.addChild(arrayAccess);
            return new MatchResult.Success(cp.next(), cp.matched(), new Assign(target, exprNode));

        }

    }

    public static class AssignStmt implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            final CParser cp = new CParser(input);
            if (!cp.testNext(new IdentifierParser())) {
                return cp.toFailure();
            }
            final Node target = cp.lastMatch();
            if (!cp.testNext(Equals.class)) {
                return cp.toFailure();
            }

            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node exprNode = cp.lastMatch();
            if (!cp.testNext(Semi.class)) {
                return cp.toFailure();
            }

            return new MatchResult.Success(cp.next(), cp.matched(), new Assign(target, exprNode));
        }

    }

    public static class IfBlock implements Parser {
        private static final Logger L = LoggerFactory.getLogger(IfBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            IfBlock.L.debug("Input : {}", input);
            final CParser cp = new CParser(input);
            if (!cp.testNext(If.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(LeftParen.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node expr = cp.lastMatch();
            if (!cp.testNext(RightParen.class)) {
                return cp.toFailure();
            }

            if (!cp.testNext(new StatementParser())) {
                return cp.toFailure();
            }
            final Node stmtThen = cp.lastMatch();

            if (!cp.testNext(Else.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(new StatementParser())) {
                return cp.toFailure();
            }
            final Node stmtElse = cp.lastMatch();

            // if cond ? goto then : goto else
            //
            return new MatchResult.Success(cp.next(), cp.matched(), new org.karaffe.compiler.tree.If(expr, stmtThen, stmtElse));
        }

    }

    public static class StmtBlock implements Parser {

        private static final Logger L = LoggerFactory.getLogger(StmtBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            StmtBlock.L.debug("Input :{}", input);
            final CParser cp = new CParser(input);
            if (!cp.testNext(LeftBrace.class)) {
                return cp.toFailure();
            }

            final List<Node> statements = new ArrayList<>();
            while (cp.testNext(new StatementParser())) {
                statements.add(cp.lastMatch());
            }
            if (!cp.testNext(RightBrace.class)) {
                return cp.toFailure();
            }
            final Block block = new Block(statements);
            return new MatchResult.Success(cp.next(), cp.matched(), block);
        }
    }

    public static class SystemOutPrintln implements Parser {

        @Override
        public MatchResult parse(final Tokens input) {
            final CParser cp = new CParser(input);
            if (!cp.testNext(KeywordToken.PrintlnFunction.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(LeftParen.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node exprNode = cp.lastMatch();
            if (!cp.testNext(RightParen.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(Semi.class)) {
                return cp.toFailure();
            }
            return new MatchResult.Success(cp.next(), cp.matched(), new Apply(new Select(new Name("java"), new Name("lang"), new Name("System"), new Name("out"), new Name("println")), exprNode));
        }

    }

    public static class WhileBlock implements Parser {
        private static final Logger L = LoggerFactory.getLogger(WhileBlock.class);

        @Override
        public MatchResult parse(final Tokens input) {
            WhileBlock.L.debug("Input :{}");
            final CParser cp = new CParser(input);
            if (!cp.testNext(While.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(LeftParen.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }
            final Node cond = cp.lastMatch();
            if (!cp.testNext(RightParen.class)) {
                return cp.toFailure();
            }
            if (!cp.testNext(new StatementParser())) {
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

    @Override
    public MatchResult parse(final Tokens input) {
        final CParser cp = new CParser(input);
        if (cp.selectFirst(
                new StmtBlock(),
                new IfBlock(),
                new VarDefParser(),
                new WhileBlock(),
                new SystemOutPrintln(),
                new AssignStmt(),
                new ArrayAssignStmt())) {
            return cp.toSuccess();
        }
        return cp.toFailure();
    }

}
