package org.karaffe.compiler.parser;

import java.util.ArrayList;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Selector;

public class PathParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final MatchResult result = TokenMatcher.concat(TokenMatcher.identifier(), TokenMatcher.zeroOrMore(TokenMatcher.manyDotIdentifier())).match(input);
        if (result.isFailure()) {
            return result;
        }
        final StringBuilder sb = new StringBuilder();
        result.matched().orElseGet(ArrayList::new).stream().map(Token::getText).forEach(sb::append);
        return new MatchResult.Success(result.next(), result.matched().orElseGet(ArrayList::new), new Selector.DefaultSelector(sb.toString()));
    }

}
