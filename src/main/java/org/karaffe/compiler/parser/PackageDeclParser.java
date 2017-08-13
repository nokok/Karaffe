package org.karaffe.compiler.parser;

import java.util.ArrayList;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.PackageDecl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackageDeclParser implements Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageDecl.class);

    @Override
    public MatchResult parse(final Tokens input) {
        PackageDeclParser.LOGGER.debug("Input : {}", input);
        final MatchResult packageDeclResult = TokenMatcher.concat(
                TokenMatcher.packageKeyword(),
                TokenMatcher.identifier(),
                TokenMatcher.manyDotIdentifier()).match(input);
        if (packageDeclResult.isFailure()) {
            PackageDeclParser.LOGGER.debug("Failure : {}", packageDeclResult);
            return packageDeclResult;
        }
        final PackageDecl packageDecl = new PackageDecl(null, null);
        final Success success = new MatchResult.Success(packageDeclResult.next(), packageDeclResult.matched().orElseGet(ArrayList::new), packageDecl);
        PackageDeclParser.LOGGER.debug("Success : {}", success);
        return success;
    }
}
