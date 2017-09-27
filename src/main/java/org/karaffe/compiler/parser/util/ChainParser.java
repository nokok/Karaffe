package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Name;

import com.google.common.base.Function;

public class ChainParser {
    public static final Function<MatchResult.Success, String> NO_OP = m -> "1";
    public static final Function<MatchResult.Success, Name> ID_NAME = c -> c.getNode().map(Name.class::cast).orElseThrow(IllegalStateException::new);
    public static final Function<MatchResult.Success, VarDef> VARDEF = c -> c.getNode().map(VarDef.class::cast).orElseThrow(IllegalStateException::new);
    public static final Function<MatchResult.Success, MethodDef> METHODDEF = c -> c.getNode().map(MethodDef.class::cast).orElseThrow(IllegalStateException::new);

    private final Tokens input;
    private Tokens nextInput;
    private final List<Token> matched = new ArrayList<>();
    private Token erroredToken = null;
    private boolean hasError = false;
    private Object lastMatch = null;

    public ChainParser(final Tokens input) {
        this.input = input;
        this.nextInput = new Tokens(new ArrayList<>(input));
    }

    public void reset() {
        this.nextInput = new Tokens(new ArrayList<>(this.input));
        this.matched.clear();
        this.hasError = false;
    }

    public <T> Optional<T> nextMatch(final TokenMatcher matcher, final Function<MatchResult.Success, T> f) {
        if (this.hasError) {
            return Optional.empty();
        }
        final MatchResult result = matcher.match(this.nextInput);
        if (result.isSuccess()) {
            this.matched.addAll(result.matchedF());
            this.nextInput = result.next();
            final T n = f.apply((Success) result);
            this.lastMatch = n;
            return Optional.of(n);
        }
        this.hasError = true;
        if (result.erroredHead().isPresent()) {
            this.erroredToken = result.erroredHead().get();
        }
        return Optional.empty();
    }

    public <T> boolean testNext(final TokenMatcher matcher, final Function<MatchResult.Success, T> f) {
        return this.testNext(matcher, f, true);
    }

    public <T> boolean testNext(final TokenMatcher matcher, final Function<MatchResult.Success, T> f, final boolean moveCursorOnSuccess) {
        final MatchResult result = matcher.match(this.nextInput);
        if (result.isSuccess()) {
            if (moveCursorOnSuccess) {
                this.matched.addAll(result.matchedF());
                this.nextInput = result.next();
            }
            final T n = f.apply((Success) result);
            this.lastMatch = n;
            return true;
        }
        return false;
    }

    public <T> T lastMatch() {
        if (this.lastMatch == null) {
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

    public MatchResult.Failure toFailure() {
        return new MatchResult.Failure(Optional.ofNullable(this.erroredToken).orElse(this.input.get(0)), this.input);
    }
}
