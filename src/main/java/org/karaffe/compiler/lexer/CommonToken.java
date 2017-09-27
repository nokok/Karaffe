package org.karaffe.compiler.lexer;

import java.util.UUID;

import org.karaffe.compiler.util.Position;

public abstract class CommonToken implements Token {

    private final String tokenId;
    private final String text;
    private final Position position;
    private final String description;

    CommonToken(final String text, final Position position, final String description) {
        this(UUID.randomUUID(), text, position, description);
    }

    CommonToken(final UUID uuid, final String text, final Position position, final String description) {
        this.tokenId = UUID.randomUUID().toString();
        this.text = text;
        this.position = position;
        this.description = description;
    }

    @Override
    public String getTokenId() {
        return this.tokenId;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static class ErrorToken extends CommonToken {
        public ErrorToken(final String text) {
            this(text, Position.noPos());
        }

        public ErrorToken(final String text, final Position position) {
            super(text, position, "error token");
        }
    }

    public static class EOF extends CommonToken {
        public EOF() {
            this(Position.noPos());
        }

        public EOF(final Position position) {
            super("", position, "end of file");
        }

        @Override
        public String toString() {
            return "EOF";
        }
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
