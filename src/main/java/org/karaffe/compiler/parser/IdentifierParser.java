package org.karaffe.compiler.parser;

import java.util.ArrayList;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.base.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentifierParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(null, input);
        }
        IdentifierParser.LOGGER.debug("Input : {}", input);

        final MatchResult idResult = TokenMatcher.create(IdentifierToken.class).match(input);
        if (idResult.isFailure()) {
            return idResult;

        }
        final IdentifierToken identifierToken = idResult.matched().map(t -> t.get(0)).map(IdentifierToken.class::cast).get(); // Not Empty

        return new MatchResult.Success(idResult.next(), idResult.matched().orElseGet(ArrayList::new), new Name(identifierToken));
    }

}
