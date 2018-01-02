package org.karaffe.compiler.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.karaffe.compiler.lexer.CommonToken.ErrorToken;
import org.karaffe.compiler.pos.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KaraffeLexer extends AbstractLexer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaraffeLexer.class);

    private final Pattern LEXER_PATTERN;

    private final boolean insertEOF;

    private final boolean insertParenMatchError;

    private final boolean replaceCR;

    public KaraffeLexer(final String source) {
        this(source, true);
    }

    public KaraffeLexer(final String filePath, final String source) {
        this(filePath, source, true, true);
    }

    public KaraffeLexer(final String source, final boolean insertEOF) {
        this(source, insertEOF, true);
    }

    public KaraffeLexer(final String source, final boolean insertEOF, final boolean insertParenMatchError) {
        this("no-file", source, insertEOF, insertParenMatchError);
    }

    public KaraffeLexer(final String filePath, final String source, final boolean insertEOF, final boolean insertParenMatchError) {
        this(filePath, source, insertEOF, insertParenMatchError, true);
    }

    public KaraffeLexer(final String filePath, final String source, final boolean insertEOF, final boolean insertParenMatchError, boolean replaceCR) {
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
        this.replaceCR = replaceCR;
    }

    @Override
    public List<Token> run() {
        int parenPair = 0;
        int bracePair = 0;
        int bracketPair = 0;
        final String target;
        if (this.replaceCR) {
            target = this.source.replaceAll("\\r\\n", "\n");
        } else {
            target = this.source;
        }
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
                final Position position = Position.of(this.filePath, matcher.start(), matcher.end(), line, column);
                if (maxMatch < text.length()) {
                    maxMatch = text.length();
                    lastToken = pattern.applyToken(position, text);
                }
                token = lastToken;
                column += token.getText().length();
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
                KaraffeLexer.LOGGER.debug("Found: {} at {}:{} -> {}:{}", String.format("%-20s", token), String.format("%3s", token.getPosition().getLineF().map(t -> t.toString()).orElse("?")), String.format("%-3s", token.getPosition().getColumnF().map(t -> t.toString()).orElse("?")), String.format("%-20s", token.getClass().getSimpleName()), token.getTokenId());
            });
            KaraffeLexer.LOGGER.debug("END");
        }

        return tokens;
    }

}
