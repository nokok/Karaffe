package org.karaffe.compiler.util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.Objects;

public class Position {
  private static final Position NO_POS = new Position();
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

  private Position() {
    this.sourceName = "<unknown>";
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

  public static Position noPos() {
    return NO_POS;
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

  public boolean isNoPos() {
    return this.equals(NO_POS);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Position position = (Position) o;
    return line == position.line &&
           endLine == position.endLine &&
           column == position.column &&
           endColumn == position.endColumn &&
           sourceName.equals(position.sourceName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(line, endLine, column, endColumn, sourceName);
  }

  @Override
  public String toString() {
    return String.format("%s:%s:%s", line, column, sourceName);
  }
}
