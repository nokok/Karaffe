package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.base.Node;

public interface MatchResult extends ResultState, ResultConverter<MatchResult.Success, MatchResult.Failure> {

    public Tokens next();

    public default Optional<Node> getNode() {
        return Optional.empty();
    }

    public default Collection<Token> matchedF() {
        return this.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new);
    }

    public default Optional<Token> errorHeadF() {
        return this.toFailure().flatMap(Failure::errorHead);
    }

    public static class Success implements MatchResult {

        private final Tokens next;
        private final Collection<Token> matchedTokens;
        private final Node node;

        public Success(final Tokens next, final Collection<Token> matchedTokens) {
            this(next, matchedTokens, new Empty());
        }

        public Success(final Tokens next, final Collection<Token> matchedTokens, final Node node) {
            this.next = next;
            this.matchedTokens = matchedTokens;
            this.node = node;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public Optional<Success> toSuccess() {
            return Optional.of(this);
        }

        @Override
        public Optional<Failure> toFailure() {
            return Optional.empty();
        }

        @Override
        public Tokens next() {
            return this.next;
        }

        @Override
        public Optional<Node> getNode() {
            return Optional.ofNullable(this.node);
        }

        Collection<Token> matchedTokens() {
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
            this(next.size() == 0 ? new CommonToken.ErrorToken("Empty") : next.iterator().next(), next);
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
        public Optional<Success> toSuccess() {
            return Optional.empty();
        }

        @Override
        public Optional<Failure> toFailure() {
            return Optional.of(this);
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
            return String.format("Failure %s %s Next: %s", this.getNode().toString(), this.errorHeadF().map(errorToken -> String.format("ErrorToken : %s at %s , ID:%s", errorToken.getDescription(), errorToken.getPosition(), errorToken.getTokenId())), this.next());
        }
    }
}
