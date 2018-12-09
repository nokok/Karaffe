package org.karaffe.compiler.util;

import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.Objects;

public class Position {
    private int line;
    private int column;
    private String sourceName;

    public Position(Token token) {
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
        this.sourceName = token.getInputStream().getSourceName();
    }

    public Position(int line, int column, String sourceName) {
        this.line = line;
        this.column = column;
        this.sourceName = Objects.requireNonNull(sourceName);
    }

    public Position(int line, int column, Recognizer<?, ?> recognizer) {
        this(line, column, recognizer.getInputStream().getSourceName());
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getSourceName() {
        return sourceName;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", line, column, sourceName);
    }
}
