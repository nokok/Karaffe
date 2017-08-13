package org.karaffe.compiler.lexer;

import org.karaffe.compiler.util.Position;

public abstract class KeywordToken extends CommonToken {

    KeywordToken(final String text, final Position position) {
        super(text, position, text + " keyword");
    }

    public static class Package extends KeywordToken {
        public Package() {
            this(Position.noPos());
        }

        public Package(final Position position) {
            super("package", position);
        }
    }

    public static class Boolean extends KeywordToken {
        public Boolean() {
            this(Position.noPos());
        }

        public Boolean(final Position position) {
            super("boolean", position);
        }
    }

    public static class Extends extends KeywordToken {
        public Extends() {
            this(Position.noPos());
        }

        public Extends(final Position position) {
            super("extends", position);
        }
    }

    public static class Length extends KeywordToken {
        public Length() {
            this(Position.noPos());
        }

        public Length(final Position position) {
            super("length", position);
        }
    }

    public static class Return extends KeywordToken {
        public Return() {
            this(Position.noPos());
        }

        public Return(final Position position) {
            super("return", position);
        }
    }

    public static class Class extends KeywordToken {
        public Class() {
            this(Position.noPos());
        }

        public Class(final Position position) {
            super("class", position);
        }
    }

    public static class False extends KeywordToken {
        public False() {
            this(Position.noPos());
        }

        public False(final Position position) {
            super("false", position);
        }
    }

    public static class While extends KeywordToken {
        public While() {
            this(Position.noPos());
        }

        public While(final Position position) {
            super("while", position);
        }
    }

    public static class True extends KeywordToken {
        public True() {
            this(Position.noPos());
        }

        public True(final Position position) {
            super("true", position);
        }
    }

    public static class Void extends KeywordToken {
        public Void() {
            this(Position.noPos());
        }

        public Void(final Position position) {
            super("void", position);
        }
    }

    public static class This extends KeywordToken {
        public This() {
            this(Position.noPos());
        }

        public This(final Position position) {
            super("this", position);
        }
    }

    public static class Else extends KeywordToken {
        public Else() {
            this(Position.noPos());
        }

        public Else(final Position position) {
            super("else", position);
        }
    }

    public static class Int extends KeywordToken {
        public Int() {
            super("int", Position.noPos());
        }

        public Int(final Position position) {
            super("int", position);
        }
    }

    public static class New extends KeywordToken {
        public New() {
            this(Position.noPos());
        }

        public New(final Position position) {
            super("new", position);
        }
    }

    public static class If extends KeywordToken {
        public If() {
            this(Position.noPos());
        }

        public If(final Position position) {
            super("if", position);
        }
    }

    public static class SystemOutPrintln extends KeywordToken {
        public SystemOutPrintln() {
            this(Position.noPos());
        }

        public SystemOutPrintln(final Position position) {
            super("System.out.println", position);
        }
    }

    @Override
    public String toString() {
        return "RES:[" + this.getText() + "]";
    }

}
