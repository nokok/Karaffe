package org.karaffe.compiler.lexer;

import org.karaffe.compiler.lexer.CommonToken.ErrorToken;
import org.karaffe.compiler.pos.Position;

public interface Token {

    public static Token EOF(final Position position) {
        return new WhitespaceToken.EOF(position);
    }

    public String getDescription();

    public Position getPosition();

    public String getText();

    public String getTokenId();

    public default boolean is(final Class<? extends Token> clazz) {
        return clazz.isInstance(this);
    }

    public default boolean isErrorToken() {
        return this instanceof ErrorToken;
    }

    public default boolean isNeedLineReset() {
        return this instanceof WhitespaceToken.NewLine;
    }

    public default boolean isWhiteSpace() {
        if (this instanceof WhitespaceToken.EOF) {
            return false;
        }
        return this instanceof WhitespaceToken;
    }

}
