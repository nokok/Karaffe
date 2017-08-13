package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class ModifierToken extends CommonToken {
    ModifierToken(final String text, final Position position) {
        super(text, position, text + " modifier");
    }

    public static class Public extends ModifierToken {
        protected Public(final Position position) {
            super("public", position);
        }
    }

    public static class Static extends ModifierToken {
        protected Static(final Position position) {
            super("static", position);
        }
    }

    public static class Private extends ModifierToken {
        protected Private(final Position position) {
            super("private", position);
        }
    }

    @Override
    public String toString() {
        return "MOD:[" + this.getText() + "]";
    }
}