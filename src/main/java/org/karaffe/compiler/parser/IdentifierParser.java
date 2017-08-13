package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.Optional;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Identifier;
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
        final TokenMatcher idTokenMatcher = TokenMatcher.identifier();
        final MatchResult idResult = idTokenMatcher.match(input);
        if (idResult.isFailure()) {
            return idResult;

        }
        final Optional<Token> token = idResult.matched().map(t -> t.get(0));

        return new MatchResult.Success(
                idResult.next(),
                idResult.matched().orElseGet(ArrayList::new),
                new Identifier(token.orElseThrow(IllegalStateException::new)));
    }

}
