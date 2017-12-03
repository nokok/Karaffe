package org.karaffe.compiler.lexer;

import java.util.function.BiFunction;

import org.karaffe.compiler.pos.Position;

public enum LexerPattern {

    // Skip Tokens
    NEWLINE("\\n", (text, pos) -> new WhitespaceToken.NewLine(pos)),
    SPACE(" ", (text, pos) -> new WhitespaceToken.Space(pos)),
    TAB("\\t", (text, pos) -> new WhitespaceToken.Tab(pos)),
    WIDESPACE("　", (text, pos) -> new WhitespaceToken.WideSpace(pos)),

    SYSTEMOUTPRINTLN("System\\.out\\.println", (text, pos) -> new KeywordToken.SystemOutPrintln(pos)), // MiniJava

    // Keywords
    PACKAGE("package", (text, pos) -> new KeywordToken.Package(pos)),
    BOOLEAN("boolean", (text, pos) -> new KeywordToken.Boolean(pos)), // MiniJava
    EXTENDS("extends", (text, pos) -> new KeywordToken.Extends(pos)),
    LENGTH("length", (text, pos) -> new KeywordToken.Length(pos)), // MiniJava
    RETURN("return", (text, pos) -> new KeywordToken.Return(pos)),
    CLASS("class", (text, pos) -> new KeywordToken.Class(pos)),
    FALSE("false", (text, pos) -> new KeywordToken.False(pos)),
    WHILE("while", (text, pos) -> new KeywordToken.While(pos)),
    TRUE("true", (text, pos) -> new KeywordToken.True(pos)),
    VOID("void", (text, pos) -> new KeywordToken.Void(pos)),
    THIS("this", (text, pos) -> new KeywordToken.This(pos)),
    ELSE("else", (text, pos) -> new KeywordToken.Else(pos)),
    INT("int", (text, pos) -> new KeywordToken.Int(pos)), // MiniJava
    NEW("new", (text, pos) -> new KeywordToken.New(pos)),
    IF("if", (text, pos) -> new KeywordToken.If(pos)),

    // Modifiers
    PRIVATE("private", (text, pos) -> new ModifierToken.Private(pos)),
    PUBLIC("public", (text, pos) -> new ModifierToken.Public(pos)),
    STATIC("static", (text, pos) -> new ModifierToken.Static(pos)),

    // Literals
    INTEGER("([1-9][0-9]*|0)", (text, pos) -> new LiteralToken.IntLiteral(text, pos)),

    // Ids
    UPPERID("[A-Z][a-zA-Z0-9]*", (text, pos) -> new IdentifierToken.TypeName(text, pos)),
    LOWERID("[a-z][a-zA-Z0-9]*", (text, pos) -> new IdentifierToken.VarName(text, pos)),

    // Operators
    // 2
    ANDAND("&&", (text, pos) -> new OperatorToken.AndAnd(pos)),

    // 1
    AND("&", (text, pos) -> new OperatorToken.And(pos)),
    DOT("\\.", (text, pos) -> new OperatorToken.Dot(pos)),
    LESSTHAN("<", (text, pos) -> new OperatorToken.LessThan(pos)),
    GREATERTHAN(">", (text, pos) -> new OperatorToken.GreaterThan(pos)),
    EQUALS("\\=", (text, pos) -> new OperatorToken.Equals(pos)),
    PLUS("\\+", (text, pos) -> new OperatorToken.Plus(pos)),
    MINUS("-", (text, pos) -> new OperatorToken.Minus(pos)),
    STAR("\\*", (text, pos) -> new OperatorToken.Star(pos)),
    SLASH("/", (text, pos) -> new OperatorToken.Slash(pos)),
    COMMA(",", (text, pos) -> new OperatorToken.Comma(pos)),
    BANG("!", (text, pos) -> new OperatorToken.Bang(pos)),
    SEMI(";", (text, pos) -> new OperatorToken.Semi(pos)),
    LBRACE("\\{", (text, pos) -> new OperatorToken.LeftBrace(pos)),
    RBRACE("\\}", (text, pos) -> new OperatorToken.RightBrace(pos)),
    LPAREN("\\(", (text, pos) -> new OperatorToken.LeftParen(pos)),
    RPAREN("\\)", (text, pos) -> new OperatorToken.RightParen(pos)),
    LBRACKET("\\[", (text, pos) -> new OperatorToken.LeftBracket(pos)),
    RBRACKET("\\]", (text, pos) -> new OperatorToken.RightBracket(pos)),

    // Error Tokens
    ERROR(".+", (text, pos) -> new CommonToken.ErrorToken(text, pos));

    private final String pattern;
    private final BiFunction<String, Position, Token> func;

    private LexerPattern(final String pattern, final BiFunction<String, Position, Token> func) {
        this.pattern = pattern;
        this.func = func;
    }

    public String getPattern() {
        return this.pattern;
    }

    public Token applyToken(final Position position, final String text) {
        return this.func.apply(text, position);
    }
}