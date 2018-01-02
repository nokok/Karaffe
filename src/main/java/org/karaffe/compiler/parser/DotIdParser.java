package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.Name;

public class DotIdParser implements Parser {

    @Override
    public MatchResult parse(Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        final CParser cp = new CParser(input);
        if (!cp.testNext(CommonToken.Dot.class)) {
            return cp.toFailure();
        }
        if (!cp.testNext(new IdentifierParser())) {
            return cp.toFailure();
        }
        Name name = cp.lastMatch();
        return new MatchResult.Success(cp.next(), cp.matched(), name);
    }

}
