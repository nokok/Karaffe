package org.karaffe.compiler.util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.Objects;

public class Position {
    private int line;
    private int endLine;
    private int column;
    private int endColumn;
    private String sourceName;

    public Position(Token token) {
        this(token.getLine(), token.getCharPositionInLine(), token.getInputStream().getSourceName());
    }

    public Position(ParserRuleContext ruleContext) {
        this.line = ruleContext.start.getLine();
        this.column = ruleContext.start.getCharPositionInLine();
        this.endLine = ruleContext.stop.getLine();
        this.endColumn = ruleContext.stop.getCharPositionInLine();
        this.sourceName = ruleContext.start.getInputStream().getSourceName();
    }

    public Position(int line, int column, String sourceName) {
        if (line <= 0) {
            throw new IllegalArgumentException("Invalid line number : " + line);
        }
        if (column < 0) {
            throw new IllegalArgumentException("Invalid column number : " + column);
        }
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

    public int getEndLine() {
        return endLine;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public String getSourceName() {
        return sourceName;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", line, column, sourceName);
    }
}
