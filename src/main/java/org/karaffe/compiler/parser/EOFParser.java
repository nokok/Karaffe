package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EOFParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EOFParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            EOFParser.LOGGER.debug("FIRST OK EOF");
            return new MatchResult.Success(new Tokens(), new Tokens());
        }
        EOFParser.LOGGER.debug("InputTokens :{}", input);
        int loopCount = 0;
        for (final Token token : input) {
            EOFParser.LOGGER.debug("Current : {}", token);
            loopCount++;
            if (token.isWhiteSpace()) {
                continue;
            }
            if (token.is(org.karaffe.compiler.lexer.CommonToken.EOF.class)) {
                if (input.size() > loopCount) {
                    EOFParser.LOGGER.debug("NG1");
                    return new MatchResult.Failure(input.get(loopCount + 1), input);
                }
                EOFParser.LOGGER.debug("OK2");
                return new MatchResult.Success(new Tokens(), new Tokens());
            }
            EOFParser.LOGGER.debug("NG2");
            return new MatchResult.Failure(token, input);
        }
        EOFParser.LOGGER.debug("BAD EOF");
        return new MatchResult.Failure(null, input);
    }

}
