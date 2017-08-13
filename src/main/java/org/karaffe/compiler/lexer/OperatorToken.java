package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class OperatorToken extends CommonToken {

    OperatorToken(final String text, final Position position) {
        super(text, position, text + " operator");
    }

    public static class AndAnd extends OperatorToken {
        public AndAnd() {
            this(Position.noPos());
        }

        public AndAnd(final Position position) {
            super("&&", position);
        }
    }

    public static class And extends OperatorToken {
        public And() {
            this(Position.noPos());
        }

        public And(final Position position) {
            super("&", position);
        }
    }

    public static class Dot extends OperatorToken {
        public Dot() {
            this(Position.noPos());
        }

        public Dot(final Position position) {
            super(".", position);
        }
    }

    public static class LessThan extends OperatorToken {
        public LessThan() {
            this(Position.noPos());
        }

        public LessThan(final Position position) {
            super("<", position);
        }
    }

    public static class GreaterThan extends OperatorToken {
        public GreaterThan() {
            this(Position.noPos());
        }

        public GreaterThan(final Position position) {
            super(">", position);
        }
    }

    public static class Equals extends OperatorToken {
        public Equals() {
            this(Position.noPos());
        }

        public Equals(final Position position) {
            super("=", position);
        }
    }

    public static class Plus extends OperatorToken {
        public Plus() {
            this(Position.noPos());
        }

        public Plus(final Position position) {
            super("+", position);
        }
    }

    public static class Minus extends OperatorToken {
        public Minus() {
            this(Position.noPos());
        }

        public Minus(final Position position) {
            super("-", position);
        }
    }

    public static class Star extends OperatorToken {
        public Star() {
            this(Position.noPos());
        }

        public Star(final Position position) {
            super("*", position);
        }
    }

    public static class Slash extends OperatorToken {
        public Slash() {
            this(Position.noPos());
        }

        public Slash(final Position position) {
            super("/", position);
        }
    }

    public static class Comma extends OperatorToken {
        public Comma() {
            this(Position.noPos());
        }

        public Comma(final Position position) {
            super(",", position);
        }
    }

    public static class Bang extends OperatorToken {
        public Bang() {
            this(Position.noPos());
        }

        public Bang(final Position position) {
            super("!", position);
        }
    }

    public static class Semi extends OperatorToken {
        public Semi() {
            this(Position.noPos());
        }

        public Semi(final Position position) {
            super(";", position);
        }
    }

    public static class LeftBrace extends OperatorToken {
        public LeftBrace() {
            this(Position.noPos());
        }

        public LeftBrace(final Position position) {
            super("{", position);
        }
    }

    public static class RightBrace extends OperatorToken {
        public RightBrace() {
            this(Position.noPos());
        }

        public RightBrace(final Position position) {
            super("}", position);
        }
    }

    public static class LeftParen extends OperatorToken {
        public LeftParen() {
            this(Position.noPos());
        }

        public LeftParen(final Position position) {
            super("(", position);
        }
    }

    public static class RightParen extends OperatorToken {
        public RightParen() {
            this(Position.noPos());
        }

        public RightParen(final Position position) {
            super(")", position);
        }
    }

    public static class LeftBracket extends OperatorToken {
        public LeftBracket() {
            this(Position.noPos());
        }

        public LeftBracket(final Position position) {
            super("[", position);
        }
    }

    public static class RightBracket extends OperatorToken {
        public RightBracket() {
            this(Position.noPos());
        }

        public RightBracket(final Position position) {
            super("]", position);
        }
    }

    @Override
    public String toString() {
        return "OPE:[" + this.getText() + "]";
    }

}
