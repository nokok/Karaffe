package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTokenMatcher implements TokenMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTokenMatcher.class);
    private final List<Class<? extends Token>> pattern;
    private final List<Class<? extends Token>> skipTokens;

    public SimpleTokenMatcher(final Class<? extends Token> clazz) {
        this(new ArrayList<>(Arrays.asList(clazz)));
    }

    public SimpleTokenMatcher(final List<Class<? extends Token>> pattern) {
        this(pattern, TokenMatcher.DEFAULT_SKIP_TOKENS);
    }

    public SimpleTokenMatcher(final List<Class<? extends Token>> pattern, final List<Class<? extends Token>> skipTokens) {
        this.pattern = pattern;
        this.skipTokens = skipTokens;
    }

    @Override
    public List<Class<? extends Token>> skipTokens() {
        return new ArrayList<>(this.skipTokens);
    }

    @Override
    public MatchResult match(final Tokens inputTokens) {
        SimpleTokenMatcher.LOGGER.debug("Input : {}", inputTokens);
        SimpleTokenMatcher.LOGGER.debug("Pattern : {}", this.pattern.stream().map(m -> m.getSimpleName()).collect(Collectors.toList()));
        Tokens current = new Tokens(new ArrayList<>(inputTokens));
        int cursor = 0;
        final List<Token> matchedTokens = new ArrayList<>();
        for (final Class<? extends Token> expected : this.pattern) {
            final MatchResult head = this.head(current, this.skipTokens);
            if (head.isFailure()) {
                return head;
            }
            final Tokens next = head.next();
            cursor += current.size() - next.size();
            current = head.next();
            if (!current.get(0).is(expected)) {
                SimpleTokenMatcher.LOGGER.debug("  -> Failure {}", current);
                return new MatchResult.Failure(current.get(0), current);
            }
            matchedTokens.add(current.remove(0));
            cursor++;
        }
        final Tokens next = new Tokens(inputTokens.subList(cursor, inputTokens.size()));
        SimpleTokenMatcher.LOGGER.debug("  -> Success {}", next);
        return new MatchResult.Success(next, matchedTokens);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.join(" ", this.pattern.stream().map(p -> p.getSimpleName()).collect(Collectors.toList())));
        return stringBuilder.toString();
    }
}
