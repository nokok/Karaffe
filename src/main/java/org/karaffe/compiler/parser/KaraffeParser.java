package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.CompileUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KaraffeParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaraffeParser.class);

    @Override
    public MatchResult parse(final Tokens tokens) {
        final MatchResult packageDeclResult = TokenMatcher.opt(new PackageDefParser()).match(tokens);
        KaraffeParser.LOGGER.debug("PackageDecl   : {}", packageDeclResult);
        final List<Token> matchedTokens = new ArrayList<>();
        Tokens next = packageDeclResult.next();
        matchedTokens.addAll(packageDeclResult.matchedF());

        final MatchResult mainClassResult = new MainClassDeclParser().match(next);
        KaraffeParser.LOGGER.debug("MainClassDecl : {}", mainClassResult);

        next = mainClassResult.next();
        matchedTokens.addAll(mainClassResult.matchedF());
        final MatchResult classdeclResult = TokenMatcher.zeroOrMore(new ClassDefParser()).match(next);
        KaraffeParser.LOGGER.debug("ClassDecls    : {}", classdeclResult);

        next = classdeclResult.next();
        matchedTokens.addAll(classdeclResult.matchedF());

        final MatchResult eofResult = new EOFParser().match(next);
        KaraffeParser.LOGGER.debug("EOF           : {}", eofResult);

        if (eofResult.isFailure()) {
            return eofResult;
        }

        next = eofResult.next();
        matchedTokens.addAll(eofResult.matchedF());

        return new MatchResult.Success(next, matchedTokens, new CompileUnit(null, null));
    }

}
