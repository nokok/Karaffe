package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;

public class TypeParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final MatchResult result = TokenMatcher.select(
                TokenMatcher.create(KeywordToken.Int.class, OperatorToken.LeftBracket.class, OperatorToken.RightBracket.class),
                TokenMatcher.create(IdentifierToken.class, OperatorToken.LeftBracket.class, OperatorToken.RightBracket.class),
                TokenMatcher.create(KeywordToken.Int.class),
                TokenMatcher.create(KeywordToken.Boolean.class),
                TokenMatcher.identifier()).match(input);
        return result;
    }

}
