package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class ModifierToken extends CommonToken {
    ModifierToken(final String text, final Position position) {
        super(text, position, text + " modifier");
    }

    public static class Public extends ModifierToken {
        public Public(final Position position) {
            super("public", position);
        }
    }

    public static class Static extends ModifierToken {
        public Static(final Position position) {
            super("static", position);
        }
    }

    public static class Private extends ModifierToken {
        public Private(final Position position) {
            super("private", position);
        }
    }

    public static class Parameter extends ModifierToken {

        public Parameter() {
            super("", Position.noPos());
        }

    }

    @Override
    public String toString() {
        return "MOD:[" + this.getText() + "]";
    }
}