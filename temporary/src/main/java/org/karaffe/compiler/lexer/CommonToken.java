package org.karaffe.compiler.lexer;

import java.util.UUID;

import org.karaffe.compiler.pos.Position;

public abstract class CommonToken implements Token {

    public static class Colon extends CommonToken {
        public Colon() {
            this(Position.noPos());
        }

        public Colon(final Position position) {
            super(":", position, "semi colon");
        }
    }

    public static class Comma extends CommonToken {
        public Comma() {
            this(Position.noPos());
        }

        public Comma(final Position position) {
            super(",", position, ",");
        }
    }

    public static class Dot extends CommonToken {
        public Dot() {
            this(Position.noPos());
        }

        public Dot(final Position position) {
            super(".", position, ".");
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

    public static class ErrorToken extends CommonToken {
        public ErrorToken(final String text) {
            this(text, Position.noPos());
        }

        public ErrorToken(final String text, final Position position) {
            super(text, position, "error token");
        }

        @Override
        public String toString() {
            return "ERROR:[" + this.getText() + "]";
        }
    }

    public static class LeftBrace extends CommonToken {
        public LeftBrace() {
            this(Position.noPos());
        }

        public LeftBrace(final Position position) {
            super("{", position, "{");
        }
    }

    public static class LeftBracket extends CommonToken {
        public LeftBracket() {
            this(Position.noPos());
        }

        public LeftBracket(final Position position) {
            super("[", position, "[");
        }
    }

    public static class LeftParen extends CommonToken {
        public LeftParen() {
            this(Position.noPos());
        }

        public LeftParen(final Position position) {
            super("(", position, "(");
        }
    }

    public static class RightBrace extends CommonToken {
        public RightBrace() {
            this(Position.noPos());
        }

        public RightBrace(final Position position) {
            super("}", position, "}");
        }
    }

    public static class RightBracket extends CommonToken {
        public RightBracket() {
            this(Position.noPos());
        }

        public RightBracket(final Position position) {
            super("]", position, "]");
        }
    }

    public static class RightParen extends CommonToken {
        public RightParen() {
            this(Position.noPos());
        }

        public RightParen(final Position position) {
            super(")", position, ")");
        }
    }

    public static class Semi extends CommonToken {
        public Semi() {
            this(Position.noPos());
        }

        public Semi(final Position position) {
            super(";", position, "semi colon");
        }
    }

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
    public String getDescription() {
        return this.description;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getTokenId() {
        return this.tokenId;
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
