package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Node;
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

        final List<Node> pathName = new ArrayList<>();
        if (!parser.nextMatch(new IdentifierParser())) {
            return parser.toFailure();
        }

        pathName.add(parser.lastMatch());

        while (parser.testNext(TokenMatcher.dot()) && parser.testNext(new IdentifierParser())) {
            final Name name = parser.lastMatch();
            pathName.add(name);
        }

        return new MatchResult.Success(parser.next(), parser.matched(), new Select(pathName));
    }

}
