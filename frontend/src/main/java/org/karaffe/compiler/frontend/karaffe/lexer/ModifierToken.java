package org.karaffe.compiler.frontend.karaffe.lexer;

import org.karaffe.compiler.base.pos.Position;

public abstract class ModifierToken extends CommonToken {
    public static class Parameter extends ModifierToken {

        public Parameter() {
            super("", Position.noPos());
        }

    }

    public static class Private extends ModifierToken {
        public Private(final Position position) {
            super("private", position);
        }
    }

    public static class Public extends ModifierToken {
        public Public() {
            this(Position.noPos());
        }

        public Public(final Position position) {
            super("public", position);
        }
    }

    public static class Static extends ModifierToken {
        public Static(final Position position) {
            super("static", position);
        }
    }

    ModifierToken(final String text, final Position position) {
        super(text, position, text + " modifier");
    }

    @Override
    public String toString() {
        return "MOD:[" + this.getText() + "]";
    }
}