package org.karaffe.compiler.parser.util;

import org.karaffe.compiler.lexer.Tokens;

public class OptionalTokenMatcher implements TokenMatcher {

    private final TokenMatcher matcher;

    public OptionalTokenMatcher(final TokenMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public MatchResult match(final Tokens tokens) {
        final MatchResult result = this.matcher.match(tokens);
        if (result.isFailure()) {
            return new MatchResult.Success(tokens);
        }
        return new MatchResult.Success(result.next());
    }

}
