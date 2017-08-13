package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.OperatorToken.Semi;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.VarDecl;

public class VarDeclParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final MatchResult matchResult = TokenMatcher.concat(new TypeParser(), TokenMatcher.identifier(), TokenMatcher.create(Semi.class)).match(input);
        if (matchResult.isFailure()) {
            return matchResult;

        }
        final List<Token> matchedTokens = matchResult.matched().orElseGet(ArrayList::new);
        final Token typeToken = matchedTokens.get(0);
        final Token varNameToken = matchedTokens.get(1);
        return new MatchResult.Success(matchResult.next(), matchedTokens, new VarDecl(varNameToken, typeToken));
    }

}
