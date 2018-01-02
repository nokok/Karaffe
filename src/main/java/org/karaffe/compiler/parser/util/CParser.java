package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.base.Node;

public class CParser {
    private final Tokens input;
    private Tokens nextInput;
    private final List<Token> matched = new ArrayList<>();
    private Token erroredToken = null;
    private boolean hasError = false;
    private Node lastMatch = null;

    public CParser(final Tokens input) {
        this.input = input;
        if (input.isEmpty()) {
            this.hasError = true;
        }
        this.nextInput = new Tokens(new ArrayList<>(input));
        for (Token token : this.nextInput) {
            if (token.isErrorToken() && this.erroredToken == null) {
                this.erroredToken = token;
                this.hasError = true;
            }
        }
    }

    public void reset() {
        this.nextInput = new Tokens(new ArrayList<>(this.input));
        this.matched.clear();
        this.hasError = false;
    }

    public boolean testNext(final Class<? extends Token> clazz) {
        return this.testNext(TokenMatcher.create(clazz), true);
    }

    public boolean testNext(final TokenMatcher matcher) {
        return this.testNext(matcher, true);
    }

    public boolean testNext(final Parser parser) {
        return this.testNext(parser, true);
    }

    public boolean testNext(final TokenMatcher matcher, final boolean moveCursorOnSuccess) {
        if (this.hasError) {
            return false;
        }
        final MatchResult result = matcher.match(this.nextInput);
        if (result.isSuccess()) {
            if (moveCursorOnSuccess) {
                this.matched.addAll(result.matchedF());
                this.nextInput = result.next();
            }
            final Node n = Node.class.cast(result.getNode().orElseThrow(IllegalStateException::new));
            this.lastMatch = n;
            return true;
        }
        this.erroredToken = result.errorHeadF().map(m -> m).orElseGet(() -> this.nextInput.isEmpty() ? null : this.nextInput.iterator().next());
        // result.errorHeadF().ifPresent(c -> this.erroredToken = c);
        return false;
    }

    public boolean selectOne(Parser... parsers) {
        int success = 0;
        Parser successParser = null;
        for (Parser parser : parsers) {
            if (this.testNext(parser, false)) {
                successParser = parser;
                success++;
            }
        }
        if (successParser == null) {
            return false;
        }
        if (success != 1) {
            return false;
        }
        return this.testNext(successParser);
    }

    public boolean selectFirst(Parser... parsers) {
        for (Parser parser : parsers) {
            if (this.testNext(parser)) {
                return true;
            }
        }
        return false;
    }

    public MatchResult chain(Function<List<Node>, Node> f, Action... actions) {
        if (this.input.isEmpty()) {
            return new MatchResult.Failure(this.input);
        }
        List<Node> nodes = new ArrayList<>();
        for (Action action : actions) {
            if (!this.testNext(action.getMatcher())) {
                return this.toFailure();
            }
            nodes.add(this.lastMatch);
        }
        return new MatchResult.Success(this.next(), this.matched(), f.apply(nodes));
    }

    // public MatchResult chainRaw(Function<List<Node>, MatchResult> f, Action... actions) {
    // List<Node> nodes = new ArrayList<>();
    // for (Action action : actions) {
    // if (!this.testNext(action.getMatcher())) {
    // return this.toFailure();
    // }
    // nodes.add(this.lastMatch);
    // }
    // return f.apply(nodes);
    // }

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
        return new MatchResult.Failure(Optional.ofNullable(this.erroredToken).orElse(this.input.iterator().next()), this.input);
    }

    public static class Action {
        private final TokenMatcher tokenMatcher;

        public Action(Class<? extends Token> clazz) {
            this.tokenMatcher = TokenMatcher.create(clazz);
        }

        public Action(Parser parser) {
            this.tokenMatcher = parser;
        }

        TokenMatcher getMatcher() {
            return this.tokenMatcher;
        }

        public static Action of(Class<? extends Token> clazz) {
            return new Action(clazz);
        }

        public static Action of(Parser parser) {
            return new Action(parser);
        }
    }
}
