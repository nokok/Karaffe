package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrTokenMatcher implements TokenMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrTokenMatcher.class);
    private final boolean first;
    private final TokenMatcher[] matchers;

    public OrTokenMatcher(final TokenMatcher... matchers) {
        this(false, matchers);
    }

    public OrTokenMatcher(final boolean first, final TokenMatcher... matchers) {
        this.first = first;
        this.matchers = matchers;
    }

    @Override
    public MatchResult match(final Tokens tokens) {
        OrTokenMatcher.LOGGER.debug(" ======== Start : {}", tokens);
        final List<Success> successes = new ArrayList<>();
        if (tokens.isEmpty()) {
            return new MatchResult.Failure(new CommonToken.ErrorToken("No Input"), tokens);
        }
        for (final TokenMatcher matcher : this.matchers) {
            OrTokenMatcher.LOGGER.debug("Matcher : {}", matcher);
            final MatchResult result = matcher.match(tokens);
            OrTokenMatcher.LOGGER.debug("  -> {}", result.getClass().getSimpleName());
            if (result.isSuccess() && this.first) {
                OrTokenMatcher.LOGGER.debug("First Mode Return : {}", result);
                return result;
            }
            if (result.isSuccess()) {
                successes.add(result.toSuccess().get());
            }
        }
        if (successes.isEmpty()) {
            OrTokenMatcher.LOGGER.debug("MatchError");
            return new MatchResult.Failure(tokens.get(0), tokens);
        }
        Success maxMatch = null;
        int maxMatchCount = 0;
        for (final Success success : successes) {
            final int currentMatch = success.matchedF().size();
            OrTokenMatcher.LOGGER.debug("CurrentMatch : {}, {}", currentMatch, success.next().size());
            if (maxMatch == null) {
                maxMatch = success;
                maxMatchCount = currentMatch;
                continue;
            }
            if (maxMatchCount == currentMatch) {
                if (maxMatch.next().size() > success.next().size()) {
                    maxMatch = success;
                }
            }
            if (maxMatchCount < currentMatch) {
                maxMatch = success;
                maxMatchCount = currentMatch;
            }
        }
        if (maxMatch == null) {
            OrTokenMatcher.LOGGER.debug("MatchError");
            return new MatchResult.Failure(tokens.get(0), tokens);
        }
        OrTokenMatcher.LOGGER.debug(" ======== Return : {} =========", maxMatch);
        return maxMatch;
    }

}
