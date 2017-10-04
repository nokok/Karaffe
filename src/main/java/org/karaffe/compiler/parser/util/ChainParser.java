package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.tree.base.Node;

public class ChainParser {
    private final Tokens input;
    private Tokens nextInput;
    private final List<Token> matched = new ArrayList<>();
    private Token erroredToken = null;
    private boolean hasError = false;
    private Object lastMatch = null;

    public ChainParser(final Tokens input) {
        this.input = input;
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }
        this.nextInput = new Tokens(new ArrayList<>(input));
    }

    public void reset() {
        this.nextInput = new Tokens(new ArrayList<>(this.input));
        this.matched.clear();
        this.hasError = false;
    }

    public boolean nextMatch(final TokenMatcher matcher) {
        return this.nextMatch(matcher, Node.class).isPresent();
    }

    @Deprecated
    public <T> Optional<T> nextMatch(final TokenMatcher matcher, final Class<T> clazz) {
        if (this.hasError) {
            return Optional.empty();
        }
        final MatchResult result = matcher.match(this.nextInput);
        if (result.isSuccess()) {
            this.matched.addAll(result.matchedF());
            this.nextInput = result.next();
            final T n = result.getNode().map(clazz::cast).orElseThrow(IllegalStateException::new);
            this.lastMatch = n;
            return Optional.of(n);
        }
        this.hasError = true;
        if (result.erroredHead().isPresent()) {
            this.erroredToken = result.erroredHead().get();
        }
        return Optional.empty();
    }

    public boolean testNext(final TokenMatcher matcher) {
        return this.testNext(matcher, Node.class, true);
    }

    public <T> boolean testNext(final TokenMatcher matcher, final Class<T> clazz) {
        return this.testNext(matcher, clazz, true);
    }

    public <T> boolean testNext(final TokenMatcher matcher, final Class<T> clazz, final boolean moveCursorOnSuccess) {
        final MatchResult result = matcher.match(this.nextInput);
        if (result.isSuccess()) {
            if (moveCursorOnSuccess) {
                this.matched.addAll(result.matchedF());
                this.nextInput = result.next();
            }
            final T n = clazz.cast(result.getNode().orElseThrow(IllegalStateException::new));
            this.lastMatch = n;
            return true;
        }
        return false;
    }

    public boolean hasLastMatch() {
        return this.lastMatch != null;
    }

    public <T> T lastMatch() {
        if (!this.hasLastMatch()) {
            throw new IllegalStateException();
        }
        return (T) this.lastMatch;
    }

    public List<Token> matched() {
        return this.matched;
    }

    public Tokens next() {
        return this.nextInput;
    }

    public boolean hasError() {
        return this.hasError;
    }

    public MatchResult.Success toSuccess() {
        return new MatchResult.Success(this.next(), this.matched(), this.lastMatch());
    }

    public MatchResult.Failure toFailure() {
        return new MatchResult.Failure(Optional.ofNullable(this.erroredToken).orElse(this.input.get(0)), this.input);
    }
}
