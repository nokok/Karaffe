package org.karaffe.compiler.lexer;

import java.util.Optional;

import org.karaffe.compiler.util.Position;

public interface Token {

    public String getTokenId();

    public String getText();

    public Position getPosition();

    public String getDescription();

    public default boolean isNeedLineReset() {
        return this instanceof WhitespaceToken.NewLine;
    }

    public default boolean isWhiteSpace() {
        if (this instanceof WhitespaceToken.EOF) {
            return false;
        }
        return this instanceof WhitespaceToken;
    }

    public default boolean isNotWhiteSpace() {
        return !this.isWhiteSpace();
    }

    public default boolean is(final Class<? extends Token> clazz) {
        return clazz.isInstance(this);
    }

    public default Optional<Token> next() {
        return Optional.empty();
    }

    public static Token Package(final Position position) {
        return new KeywordToken.Package(position);
    }

    public static Token Class(final Token token) {
        return new KeywordToken.Class(token.getPosition());
    }

    public static Token EOF(final Position position) {
        return new WhitespaceToken.EOF(position);
    }

}
