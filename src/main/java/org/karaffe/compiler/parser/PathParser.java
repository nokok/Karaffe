package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
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
        final CParser cp = new CParser(input);

        final List<Node> pathName = new ArrayList<>();
        if (!cp.testNext(new IdentifierParser())) {
            return cp.toFailure();
        }

        pathName.add(cp.lastMatch());

        while (cp.testNext(TokenMatcher.dot()) && cp.testNext(new IdentifierParser())) {
            final Name name = cp.lastMatch();
            pathName.add(name);
        }

        return new MatchResult.Success(cp.next(), cp.matched(), new Select(pathName));
    }

}
