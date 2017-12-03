package org.karaffe.compiler.lexer;

import java.util.List;

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

    @Override
    public abstract List<Token> run();
}
