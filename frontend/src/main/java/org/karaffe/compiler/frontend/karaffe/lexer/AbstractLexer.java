package org.karaffe.compiler.frontend.karaffe.lexer;

public abstract class AbstractLexer implements Lexer {

    protected final String filePath;
    protected final String source;

    public AbstractLexer(final String source) {
        this.filePath = "no-file";
        this.source = source;
    }

    public AbstractLexer(final String filePath, final String source) {
        this.filePath = filePath;
        this.source = source;
    }
}
