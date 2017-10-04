package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.PackageDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackageDefParser implements Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageDef.class);

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        PackageDefParser.LOGGER.debug("Input : {}", input);
        final ChainParser cp = new ChainParser(input);
        if (!cp.nextMatch(TokenMatcher.packageKeyword())) {
            return cp.toFailure();
        }
        if (!cp.nextMatch(new PathParser())) {
            return cp.toFailure();
        }

        final PackageDef packageDef = new PackageDef(cp.lastMatch());
        return new MatchResult.Success(cp.next(), cp.matched(), packageDef);
    }
}
