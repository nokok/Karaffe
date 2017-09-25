package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.OperatorToken.Dot;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        PathParser.LOGGER.debug("Input : {}", input);
        final List<Name> selectedName = new ArrayList<>();
        final List<Token> matched = new ArrayList<>();
        final MatchResult firstIdMatch = new IdentifierParser().match(input);
        PathParser.LOGGER.debug("First Id : {}", firstIdMatch);
        if (firstIdMatch.isFailure()) {
            return firstIdMatch;
        }

        selectedName.add(firstIdMatch.getNode().map(Name.class::cast).orElseThrow(IllegalStateException::new));
        Tokens before = firstIdMatch.next();
        matched.addAll(firstIdMatch.matchedF());
        MatchResult last = null;
        while (before.size() > 0 || last != null && last.isSuccess()) {
            PathParser.LOGGER.debug("Before : {}", before);
            final MatchResult dotM = TokenMatcher.create(Dot.class).match(before);
            if (dotM.isFailure()) {
                break;
            }
            before = dotM.next();
            matched.addAll(dotM.matchedF());
            final MatchResult idM = new IdentifierParser().match(before);

            if (idM.isFailure()) {
                // path.to.
                return new MatchResult.Failure(before);
            }
            selectedName.add(idM.getNode().map(Name.class::cast).orElseThrow(IllegalStateException::new));
            before = idM.next();
            matched.addAll(idM.matched().orElseGet(ArrayList::new));
            last = idM;
        }

        return new MatchResult.Success(before, matched, new Select(selectedName));
    }

}
