package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.CommonToken.Dot;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.CParser.Action;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.Name;

public class DotIdParser implements Parser {

    @Override
    public MatchResult parse(Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        final CParser cp = new CParser(input);
        return cp.chain(nodes -> {
            return (Name) nodes.get(1);
        },
                Action.of(Dot.class),
                Action.of(new IdentifierParser()));
    }

}
