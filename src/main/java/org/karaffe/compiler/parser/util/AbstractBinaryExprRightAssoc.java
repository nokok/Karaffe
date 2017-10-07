package org.karaffe.compiler.parser.util;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.BinaryExpr;
import org.karaffe.compiler.tree.base.Node;

public class AbstractBinaryExprRightAssoc implements Parser {
    private final Parser upper;
    private final TokenMatcher[] parsers;

    public AbstractBinaryExprRightAssoc(final Parser upper, final TokenMatcher... parsers) {
        this.upper = upper;
        this.parsers = parsers;
    }

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        final CParser cp = new CParser(input);
        if (!cp.testNext(this.upper)) {
            return cp.toFailure();
        }
        final Node leftExpr = cp.lastMatch();
        boolean isBreak = false;
        for (final TokenMatcher p : this.parsers) {
            if (cp.testNext(p)) {
                isBreak = true;
                break;
            }
        }
        if (!isBreak) {
            return cp.toFailure();
        }
        final Node operator = cp.lastMatch();
        if (!cp.testNext(this.upper)) {
            return cp.toFailure();
        }
        final Node rightExpr = cp.lastMatch();
        return new MatchResult.Success(cp.next(), cp.matched(), new BinaryExpr(leftExpr, operator, rightExpr));
    }
}
