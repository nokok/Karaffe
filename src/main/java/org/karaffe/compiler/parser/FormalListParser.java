package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.OperatorToken.Comma;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.MatchResult.Success;
import org.karaffe.compiler.parser.util.ManyTokenMatcher;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Argument;
import org.karaffe.compiler.tree.Arguments;

public class FormalListParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final TokenMatcher formalHead = TokenMatcher.concat(new TypeParser(), TokenMatcher.identifier());

        final MatchResult headResult = formalHead.match(input);
        if (headResult.isFailure()) {
            return headResult;
        }

        final List<Argument> arguments = new ArrayList<>();
        final Success head = headResult.toSuccess().get();
        final List<Token> matchedHead = head.matched().get();
        final Argument argument = new Argument(matchedHead.get(0), matchedHead.get(1));
        arguments.add(argument);

        final TokenMatcher formalRest = TokenMatcher.concat(TokenMatcher.create(Comma.class), new TypeParser(), TokenMatcher.identifier());
        final ManyTokenMatcher repeatableTokenMatcher = new ManyTokenMatcher(formalRest);
        final MatchResult formalRestResult = repeatableTokenMatcher.match(headResult.next());
        if (headResult.isFailure() && formalRestResult.isFailure()) {
            return formalRestResult;
        } else if (headResult.isSuccess() && formalRestResult.isFailure()) {
            return new MatchResult.Success(headResult.next(), headResult.matched().orElseGet(ArrayList::new), new Arguments(arguments));
        }

        final List<Token> matchedRest = formalRestResult.matched().get();

        for (int i = 0; i < matchedRest.size(); i += 3) {
            final Argument arg = new Argument(matchedRest.get(i + 1), matchedRest.get(i + 2));
            arguments.add(arg);
        }

        matchedHead.addAll(matchedRest);

        return new MatchResult.Success(formalRestResult.next(), matchedHead, new Arguments(arguments));
    }

}
