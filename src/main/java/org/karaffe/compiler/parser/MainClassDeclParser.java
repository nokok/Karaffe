package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.KeywordToken.Void;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.ModifierToken.Static;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.LeftParen;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightParen;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;

public class MainClassDeclParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        return TokenMatcher.concat(TokenMatcher.classKeyword(),
                TokenMatcher.identifier(),
                TokenMatcher.create(LeftBrace.class),
                TokenMatcher.create(Public.class),
                TokenMatcher.create(Static.class),
                TokenMatcher.create(Void.class),
                TokenMatcher.identifier(),
                TokenMatcher.create(LeftParen.class),
                new TypeParser(),
                TokenMatcher.identifier(),
                TokenMatcher.create(RightParen.class),
                TokenMatcher.create(LeftBrace.class),
                new StatementParser(),
                TokenMatcher.create(RightBrace.class),
                TokenMatcher.create(RightBrace.class)).match(input);
    }

}
