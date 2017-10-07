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
        final ChainParser parser = new ChainParser(input);
        if (!parser.testNext(this.upper)) {
            return parser.toFailure();
        }
        final Node leftExpr = parser.lastMatch();
        boolean isBreak = false;
        for (final TokenMatcher p : this.parsers) {
            if (parser.testNext(p)) {
                isBreak = true;
                break;
            }
        }
        if (!isBreak) {
            return parser.toFailure();
        }
        final Node operator = parser.lastMatch();
        if (!parser.testNext(this.upper)) {
            return parser.toFailure();
        }
        final Node rightExpr = parser.lastMatch();
        return new MatchResult.Success(parser.next(), parser.matched(), new BinaryExpr(leftExpr, operator, rightExpr));
    }
}
