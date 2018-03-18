package org.karaffe.compiler.frontend.karaffe.lexer;

import org.karaffe.compiler.base.pos.Position;

public abstract class OperatorToken extends CommonToken {

    public static class And extends OperatorToken {
        public And() {
            this(Position.noPos());
        }

        public And(final Position position) {
            super("&", position);
        }
    }

    public static class AndAnd extends OperatorToken {
        public AndAnd() {
            this(Position.noPos());
        }

        public AndAnd(final Position position) {
            super("&&", position);
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

    public static class Equals extends OperatorToken {
        public Equals() {
            this(Position.noPos());
        }

        public Equals(final Position position) {
            super("=", position);
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

    public static class LessThan extends OperatorToken {
        public LessThan() {
            this(Position.noPos());
        }

        public LessThan(final Position position) {
            super("<", position);
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

    public static class Plus extends OperatorToken {
        public Plus() {
            this(Position.noPos());
        }

        public Plus(final Position position) {
            super("+", position);
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

    public static class Star extends OperatorToken {
        public Star() {
            this(Position.noPos());
        }

        public Star(final Position position) {
            super("*", position);
        }
    }

    OperatorToken(final String text, final Position position) {
        super(text, position, text + " operator");
    }

    @Override
    public String toString() {
        return "OPE:[" + this.getText() + "]";
    }

}
