package org.karaffe.compiler.lexer;

import org.karaffe.compiler.pos.Position;

public abstract class IdentifierToken extends CommonToken {

    public static class TypeName extends IdentifierToken {
        public TypeName(final String text) {
            this(text, Position.noPos());
        }

        public TypeName(final String text, final Position position) {
            super(text, position);
        }
    }

    public static class VarName extends IdentifierToken {
        public VarName(final String text) {
            this(text, Position.noPos());
        }

        public VarName(final String text, final Position position) {
            super(text, position);
        }
    }

    IdentifierToken(final String text, final Position position) {
        super(text, position, "identifier");
    }

    @Override
    public String toString() {
        return "IDT:[" + this.getText() + "]";
    }

}
