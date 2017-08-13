package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class WhitespaceToken extends CommonToken {

    WhitespaceToken(final String text, final Position position, final String description) {
        super(text, position, description);
    }

    public static class Tab extends WhitespaceToken {
        public Tab() {
            this(Position.noPos());
        }

        public Tab(final Position position) {
            super("\t", position, "tab");
        }
    }

    public static class Space extends WhitespaceToken {
        public Space() {
            this(Position.noPos());
        }

        public Space(final Position position) {
            super(" ", position, "space");
        }
    }

    public static class WideSpace extends WhitespaceToken {
        public WideSpace() {
            this(Position.noPos());
        }

        public WideSpace(final Position position) {
            super("　", position, "wide space");
        }
    }

    public static class NewLine extends WhitespaceToken {
        public NewLine() {
            this(Position.noPos());
        }

        public NewLine(final Position position) {
            super("\n", position, "new line");
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
