package org.karaffe.compiler.parser.util;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.BinaryExpr;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.Traceable;

public abstract class AbstractBinaryExprLeftAssoc implements Parser, Traceable {
    private final Parser upper;
    private final TokenMatcher[] parsers;

    private int maxLoop = 0;
    private Node lastNode = null;

    public AbstractBinaryExprLeftAssoc(final Parser upper, final TokenMatcher... parsers) {
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
        Node leftExpr = cp.lastMatch();
        int loopCount = 0;
        while (true) {
            boolean isBreak = false;
            for (final TokenMatcher p : this.parsers) {
                if (cp.testNext(p)) {
                    isBreak = true;
                    break;
                }
            }
            if (!isBreak) {
                break;
            }
            final Node operator = cp.lastMatch();
            if (!cp.testNext(this.upper)) {
                break;
            }
            final Node rightExpr = cp.lastMatch();
            leftExpr = new BinaryExpr(leftExpr, operator, rightExpr);
            this.lastNode = ((BinaryExpr) leftExpr).toApplyNode();
            loopCount++;
        }
        if (this.maxLoop < loopCount) {
            this.maxLoop = loopCount;
            this.lastNode = leftExpr;
        }
        this.debug("loopCount:{}", loopCount);
        this.debug("left: {}", leftExpr);
        if (leftExpr instanceof BinaryExpr) {
            this.debug("apply node: {}", ((BinaryExpr) leftExpr).toApplyNode());
        }
        if (leftExpr instanceof BinaryExpr) {
            return new MatchResult.Success(cp.next(), cp.matched(), ((BinaryExpr) leftExpr).toApplyNode());
        }
        return new MatchResult.Success(cp.next(), cp.matched(), loopCount == 0 ? leftExpr : this.lastNode);
    }
}
