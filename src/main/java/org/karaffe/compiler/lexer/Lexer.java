package org.karaffe.compiler.lexer;

import java.util.List;

public abstract class Lexer {

    protected final String filePath;
    protected final String source;

    public Lexer(final String source) {
        this.filePath = "no-file";
        this.source = source;
    }

    public Lexer(final String filePath, final String source) {
        this.filePath = filePath;
        this.source = source;
    }

    public abstract List<Token> run();
}
