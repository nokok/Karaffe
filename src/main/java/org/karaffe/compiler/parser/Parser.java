package org.karaffe.compiler.parser;

import java.util.List;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;

public interface Parser extends TokenMatcher {

    public default MatchResult parse(final List<Token> tokens) {
        return this.parse(new Tokens(tokens));
    }

    public MatchResult parse(Tokens input);

    @Override
    default MatchResult match(final Tokens tokens) {
        return this.parse(tokens);
    }
}
