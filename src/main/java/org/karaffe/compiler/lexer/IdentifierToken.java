package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class IdentifierToken extends CommonToken {

    IdentifierToken(final String text, final Position position) {
        super(text, position, "identifier");
    }

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

    @Override
    public String toString() {
        return "IDT:[" + this.getText() + "]";
    }

}
