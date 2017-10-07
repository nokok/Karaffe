package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.KeywordToken.Package;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
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
        final CParser cp = new CParser(input);
        if (!cp.testNext(Package.class)) {
            return cp.toFailure();
        }
        if (!cp.testNext(new PathParser())) {
            return cp.toFailure();
        }

        final PackageDef packageDef = new PackageDef(cp.lastMatch());
        return new MatchResult.Success(cp.next(), cp.matched(), packageDef);
    }
}
