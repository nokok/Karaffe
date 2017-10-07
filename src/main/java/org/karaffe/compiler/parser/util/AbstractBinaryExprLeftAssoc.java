package org.karaffe.compiler.parser.util;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.BinaryExpr;
import org.karaffe.compiler.tree.base.Node;

public abstract class AbstractBinaryExprLeftAssoc implements Parser {
    private final Parser upper;
    private final TokenMatcher[] parsers;

    public AbstractBinaryExprLeftAssoc(final Parser upper, final TokenMatcher... parsers) {
        this.upper = upper;
        this.parsers = parsers;
    }

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        final ChainParser parser = new ChainParser(input);
        if (!parser.testNext(this.upper)) {
            return parser.toFailure();
        }
        Node leftExpr = parser.lastMatch();
        int loopCount = 0;
        while (true) {
            boolean isBreak = false;
            for (final TokenMatcher p : this.parsers) {
                if (parser.testNext(p)) {
                    isBreak = true;
                    break;
                }
            }
            if (!isBreak) {
                break;
            }
            final Node operator = parser.lastMatch();
            if (!parser.testNext(this.upper)) {
                return parser.toFailure();
            }
            final Node rightExpr = parser.lastMatch();
            leftExpr = new BinaryExpr(leftExpr, operator, rightExpr);
            loopCount++;
        }
        return new MatchResult.Success(parser.next(), parser.matched(), loopCount == 0 ? leftExpr : ((BinaryExpr) leftExpr).toApplyNode());
    }
}
