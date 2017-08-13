package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class LiteralToken extends CommonToken {

    LiteralToken(final String text, final Position position, final String description) {
        super(text, position, description);
    }

    public static class IntLiteral extends LiteralToken {
        private final int value;

        public IntLiteral(final String text) {
            this(text, Position.noPos());
        }

        public IntLiteral(final String text, final Position position) {
            super(text, position, "Integer Literal");
            this.value = Integer.parseInt(text);
        }

        public int getValue() {
            return this.value;
        }
    }

    @Override
    public String toString() {
        return "LIT:[" + this.getText() + "]";
    }

}
