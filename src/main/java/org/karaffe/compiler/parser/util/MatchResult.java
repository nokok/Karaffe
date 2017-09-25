package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.tree.base.Empty;
import org.karaffe.compiler.tree.base.Node;

public interface MatchResult extends NodeContainer {

    public boolean isSuccess();

    public default boolean isFailure() {
        return !this.isSuccess();
    }

    public Tokens next();

    @Override
    public default Optional<Node> getNode() {
        return Optional.empty();
    }

    public default Optional<List<Token>> matched() {
        if (this.isSuccess()) {
            final Success s = (Success) this;
            return Optional.of(s.matchedTokens());
        }
        return Optional.empty();
    }

    public default List<Token> matchedF() {
        return this.matched().orElseGet(ArrayList::new);
    }

    public default Optional<Token> erroredHead() {
        if (this.isFailure()) {
            final Failure f = (Failure) this;
            return f.errorHead();
        }
        return Optional.empty();
    }

    public default Optional<Success> toSuccess() {
        if (this instanceof Success) {
            return Optional.of((Success) this);
        }
        return Optional.empty();
    }

    public default Optional<Failure> toFailure() {
        if (this instanceof Failure) {
            return Optional.of((Failure) this);
        }
        return Optional.empty();
    }

    public static class Success implements MatchResult {

        private final Tokens next;
        private final List<Token> matchedTokens;
        private final Node node;

        public Success(final Tokens next, final List<Token> matchedTokens) {
            this(next, matchedTokens, new Empty());
        }

        public Success(final Tokens next, final List<Token> matchedTokens, final Node node) {
            this.next = next;
            this.matchedTokens = matchedTokens;
            this.node = node;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public Tokens next() {
            return this.next;
        }

        @Override
        public Optional<Node> getNode() {
            return Optional.ofNullable(this.node);
        }

        List<Token> matchedTokens() {
            return this.matchedTokens;
        }

        @Override
        public String toString() {
            return String.format("Success Node:%s %s Next: %s", this.getNode(), this.matchedF().toString(), this.next());
        }
    }

    public static class Failure implements MatchResult {
        private final Token erroredToken;
        private final Tokens next;

        public Failure(final Tokens next) {
            this(next.get(0), next);
        }

        public Failure(final Token erroredToken, final Tokens next) {
            this.erroredToken = erroredToken;
            this.next = next;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public Tokens next() {
            return this.next;
        }

        Optional<Token> errorHead() {
            return Optional.ofNullable(this.erroredToken);
        }

        @Override
        public String toString() {
            return String.format("Failure %s %s Next: %s", this.getNode().toString(), this.erroredHead().map(errorToken -> String.format("ErrorToken : %s at %s , ID:%s", errorToken.getDescription(), errorToken.getPosition(), errorToken.getTokenId())), this.next());
        }
    }
}
