package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.VarName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        PathParser.LOGGER.debug("Input : {}", input);
        final ChainParser parser = new ChainParser(input);

        final List<VarName> pathName = new ArrayList<>();
        final Optional<VarName> head = parser.nextMatch(new IdentifierParser(), VarName.class);
        if (!head.isPresent()) {
            return parser.toFailure();
        }

        pathName.add(head.get());

        while (parser.testNext(TokenMatcher.dot()) && parser.testNext(new IdentifierParser())) {
            final VarName name = parser.lastMatch();
            pathName.add(name);
        }

        return new MatchResult.Success(parser.next(), parser.matched(), new Select(pathName));
    }

}
