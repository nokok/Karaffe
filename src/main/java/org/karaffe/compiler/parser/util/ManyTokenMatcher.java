package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult.Success;

public class ManyTokenMatcher implements TokenMatcher {

    private final TokenMatcher matcher;
    private final int repeat;

    public ManyTokenMatcher(final TokenMatcher matcher) {
        this(matcher, -1);
    }

    public ManyTokenMatcher(final TokenMatcher matcher, final int repeat) {
        this.matcher = matcher;
        this.repeat = repeat;
    }

    @Override
    public MatchResult match(final Tokens tokens) {
        int loopCount = this.repeat;
        final List<Token> matchedTokens = new ArrayList<>();

        Tokens target = new Tokens(new ArrayList<>(tokens));

        MatchResult last = null;
        do {
            final MatchResult matchResult = this.matcher.match(target);
            if (last == null) {
                last = matchResult;
            }
            target = matchResult.next();
            if (matchResult.isFailure() && last.isFailure()) {
                return matchResult;
            } else if (matchResult.isFailure() && last.isSuccess()) {
                return new MatchResult.Success(target, matchedTokens);
            }
            matchedTokens.addAll(matchResult.toSuccess().map(Success::matchedTokens).orElseGet(ArrayList::new));
            loopCount--;
        } while (loopCount != 0);

        return new MatchResult.Success(target, matchedTokens);
    }

}
