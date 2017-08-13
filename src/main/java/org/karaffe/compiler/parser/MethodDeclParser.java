package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.KeywordToken.Return;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.LeftParen;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightParen;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;

public class MethodDeclParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        return TokenMatcher.concat(
                TokenMatcher.create(Public.class),
                new TypeParser(),
                TokenMatcher.identifier(),
                TokenMatcher.create(LeftParen.class),
                TokenMatcher.opt(new FormalListParser()),
                TokenMatcher.create(RightParen.class),
                TokenMatcher.create(LeftBrace.class),
                TokenMatcher.zeroOrMore(new VarDeclParser()),
                TokenMatcher.zeroOrMore(new StatementParser()),
                TokenMatcher.create(Return.class),
                new ExprParser(),
                TokenMatcher.create(RightBrace.class)).match(input);
    }

}
