package org.karaffe.compiler.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.karaffe.compiler.lexer.CommonToken.ErrorToken;
import org.karaffe.compiler.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KaraffeLexer extends Lexer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaraffeLexer.class);

    private enum LexerPattern {

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

    private final Pattern LEXER_PATTERN;

    private final boolean insertEOF;

    private final boolean insertParenMatchError;

    public KaraffeLexer(final String source) {
        this(source, true);
    }

    public KaraffeLexer(final String filePath, final String source) {
        this(filePath, source, true, true);
    }

    public KaraffeLexer(final String source, final boolean insertEOF) {
        this(source, insertEOF, true);
    }

    public KaraffeLexer(final String filePath, final String source, final boolean insertEOF) {
        this(filePath, source, insertEOF, true);
    }

    public KaraffeLexer(final String source, final boolean insertEOF, final boolean insertParenMatchError) {
        this("no-file", source, insertEOF, insertParenMatchError);
    }

    public KaraffeLexer(final String filePath, final String source, final boolean insertEOF, final boolean insertParenMatchError) {
        super(filePath, source);
        final StringBuilder pat = new StringBuilder();
        KaraffeLexer.LOGGER.debug("OriginalSource: {}", source);

        for (final LexerPattern tokenType : LexerPattern.values()) {
            pat.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.getPattern()));
        }
        this.LEXER_PATTERN = Pattern.compile(pat.toString().substring(1));

        KaraffeLexer.LOGGER.debug("LexerPattern : {}", this.LEXER_PATTERN);
        this.insertEOF = insertEOF;
        this.insertParenMatchError = insertParenMatchError;
    }

    @Override
    public List<Token> run() {
        int parenPair = 0;
        int bracePair = 0;
        int bracketPair = 0;

        final String target = this.source.replaceAll("\\r\\n", "\n");
        final List<Token> tokens = new ArrayList<>();

        int line = 1;
        int column = 0;
        final Matcher matcher = this.LEXER_PATTERN.matcher(target);
        while (matcher.find()) {
            KaraffeLexer.LOGGER.debug("Find : {}:{} {}", matcher.start(), matcher.end(), matcher.group());
            Token token = null;

            int maxMatch = 0;
            Token lastToken = null;
            for (final LexerPattern pattern : LexerPattern.values()) {
                final String text = matcher.group(pattern.name());
                if (text == null) {
                    continue;
                }
                column += text.length();
                final Position position = Position.of(this.filePath, matcher.start(), matcher.end(), line, column);
                if (maxMatch < text.length()) {
                    maxMatch = text.length();
                    lastToken = pattern.applyToken(position, text);
                }
                token = lastToken;
            }
            if (token == null) {
                throw new IllegalStateException("null token");
            }

            if (!tokens.isEmpty()) {
                final Token beforeToken = tokens.get(tokens.size() - 1);
                final String concatTokenString = beforeToken.getText() + token.getText();
                KaraffeLexer.LOGGER.debug("concat: {}", concatTokenString);
                if (!beforeToken.isWhiteSpace() && concatTokenString.matches(LexerPattern.UPPERID.getPattern())) {
                    tokens.remove(beforeToken);
                    tokens.add(new IdentifierToken.TypeName(concatTokenString, Position.of(beforeToken.getPosition(), token.getPosition())));
                    KaraffeLexer.LOGGER.debug("concat 1");
                    continue;
                }
                if (!beforeToken.isWhiteSpace() && concatTokenString.matches(LexerPattern.LOWERID.getPattern())) {
                    tokens.remove(beforeToken);
                    tokens.add(new IdentifierToken.VarName(concatTokenString, Position.of(beforeToken.getPosition(), token.getPosition())));
                    KaraffeLexer.LOGGER.debug("concat 2");
                    continue;
                }
            }

            if (token.isNeedLineReset()) {
                line++;
                column = 0;
            }
            if (token.is(CommonToken.LeftParen.class)) {
                parenPair++;
            } else if (token.is(CommonToken.RightParen.class)) {
                parenPair--;
            }
            if (token.is(CommonToken.LeftBrace.class)) {
                bracePair++;
            } else if (token.is(CommonToken.RightBrace.class)) {
                bracePair--;
            }
            if (token.is(CommonToken.LeftBracket.class)) {
                bracketPair++;
            } else if (token.is(CommonToken.RightBracket.class)) {
                bracketPair--;
            }
            KaraffeLexer.LOGGER.debug("Added. {} {}", token, token.getPosition());
            tokens.add(token);
        }
        if (this.insertEOF) {
            tokens.add(Token.EOF(Position.noPos()));
        }

        if (this.insertParenMatchError) {
            if (parenPair != 0) {
                tokens.add(new CommonToken.ErrorToken("() pair failed.", Position.noPos()));
            }
            if (bracePair != 0) {
                tokens.add(new CommonToken.ErrorToken("{} pair failed.", Position.noPos()));
            }
            if (bracketPair != 0) {
                tokens.add(new CommonToken.ErrorToken("[] pair failed.", Position.noPos()));
            }
        }
        if (KaraffeLexer.LOGGER.isDebugEnabled()) {
            tokens.stream().filter(t -> t.is(ErrorToken.class)).forEach(token -> {
                KaraffeLexer.LOGGER.error("ErrorToken Found. : {} at {}", token.getText(), token.getPosition());
            });
            KaraffeLexer.LOGGER.debug("Lexing Completed.");
        }

        KaraffeLexer.LOGGER.debug("FINAL");

        if (KaraffeLexer.LOGGER.isDebugEnabled()) {
            KaraffeLexer.LOGGER.debug("===Lex Tree START===");
            final StringBuilder sb = new StringBuilder("\n\n");
            tokens.stream().forEach(token -> {
                sb.append(token.toString()).append(" ");
                if (token.isNeedLineReset()) {
                    sb.append(System.lineSeparator());
                }
            });
            KaraffeLexer.LOGGER.debug("{}\n", sb.toString());
            KaraffeLexer.LOGGER.debug("===Lex Tree END===");
        }

        if (KaraffeLexer.LOGGER.isDebugEnabled()) {
            tokens.stream().forEach(token -> {
                KaraffeLexer.LOGGER.debug("Found: {} at {}:{} -> {}:{}", String.format("%-20s", token), String.format("%3s", token.getPosition().getLineF().orElse(-1)), String.format("%-3s", token.getPosition().getColumnF().orElse(-1)), String.format("%-10s", token.getClass().getSimpleName()), token.getTokenId());
            });
            KaraffeLexer.LOGGER.debug("END");
        }

        return tokens;
    }

}
