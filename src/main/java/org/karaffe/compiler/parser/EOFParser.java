package org.karaffe.compiler.parser;

import java.util.Iterator;

import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EOFParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EOFParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        Iterator<Token> tokenIter = input.iterator();
        if (!tokenIter.hasNext()) {
            return new MatchResult.Success(new Tokens(), new Tokens());
        }
        while (tokenIter.hasNext()) {
            Token token = tokenIter.next();
            if (token.isWhiteSpace()) {
                continue;
            }
            if (token.is(EOF.class)) {
                if (tokenIter.hasNext()) {
                    return new MatchResult.Failure(tokenIter.next(), input);
                }
                return new MatchResult.Success(new Tokens(), new Tokens());
            } else {
                return new MatchResult.Failure(token, input);
            }
        }
        EOFParser.LOGGER.debug("BAD EOF");
        return new MatchResult.Failure(null, input);
    }

}
