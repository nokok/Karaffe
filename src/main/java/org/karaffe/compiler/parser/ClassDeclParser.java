package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken.Extends;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;

public class ClassDeclParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        return TokenMatcher.concat(
                TokenMatcher.classKeyword(),
                TokenMatcher.identifier(),
                TokenMatcher.opt(TokenMatcher.create(Extends.class, IdentifierToken.class)),
                TokenMatcher.opt(TokenMatcher.concat(TokenMatcher.create(LeftBrace.class),
                        TokenMatcher.zeroOrMore(new VarDefParser()),
                        TokenMatcher.zeroOrMore(new MethodDefParser()),
                        TokenMatcher.create(RightBrace.class))))
                .match(input);
    }

}
