package org.karaffe.compiler.lexer;

import java.util.List;

public abstract class Lexer {

    protected final String source;

    public Lexer(final String source) {
        this.source = source;
    }

    public abstract List<Token> run();
}
