package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackageDefParser implements Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageDef.class);

    @Override
    public MatchResult parse(final Tokens input) {
        final List<Token> matched = new ArrayList<>();
        PackageDefParser.LOGGER.debug("Input : {}", input);
        final MatchResult packageKeyword = TokenMatcher.packageKeyword().match(input);
        if (packageKeyword.isFailure()) {
            return packageKeyword;
        }
        matched.addAll(packageKeyword.matchedF());

        final MatchResult pathMatch = new PathParser().match(packageKeyword.next());

        if (pathMatch.isFailure()) {
            return pathMatch;
        }

        matched.addAll(pathMatch.matchedF());
        final PackageDef packageDecl = new PackageDef(null, pathMatch.getNode().map(Select.class::cast).orElseThrow(IllegalStateException::new));
        final Success success = new MatchResult.Success(pathMatch.next(), matched, packageDecl);
        PackageDefParser.LOGGER.debug("Success : {}", success);
        return success;
    }
}
