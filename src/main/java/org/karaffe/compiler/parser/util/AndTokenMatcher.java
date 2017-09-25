package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndTokenMatcher implements TokenMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndTokenMatcher.class);
    private final TokenMatcher[] matchers;

    public AndTokenMatcher(final TokenMatcher... matchers) {
        this.matchers = matchers;
        AndTokenMatcher.LOGGER.debug("Matcher : {}", Stream.of(matchers).map(TokenMatcher::toString).reduce((m1, m2) -> m1 + "," + m2).get());
    }

    @Override
    public MatchResult match(final Tokens tokens) {
        AndTokenMatcher.LOGGER.debug("Input : {}", tokens);
        Tokens before = new Tokens(new ArrayList<>(tokens));
        MatchResult last = null;
        final List<Token> matched = new ArrayList<>();
        for (final TokenMatcher matcher : this.matchers) {
            AndTokenMatcher.LOGGER.debug("Matcher : {}", matcher);
            final MatchResult result = matcher.match(before);
            AndTokenMatcher.LOGGER.debug("  -> {}", result.getClass().getSimpleName());
            if (result.isFailure()) {
                return new MatchResult.Failure(result.erroredHead().orElseGet(() -> tokens.get(0)), tokens);
            }
            last = result;
            before = result.next();
            matched.addAll(result.matchedF());
        }
        if (last == null) {
            return new MatchResult.Failure(tokens.get(0), tokens);
        }
        return new MatchResult.Success(last.next(), matched, last.getNode().orElse(null));
    }

    @Override
    public String toString() {
        return String.join(" -> ", Stream.of(this.matchers).map(TokenMatcher::toString).collect(Collectors.toList()));
    }
}
