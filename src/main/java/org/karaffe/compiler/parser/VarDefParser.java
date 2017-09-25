package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.OperatorToken.Semi;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Name;

public class VarDefParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final List<Token> matched = new ArrayList<>();
        final MatchResult typeResult = new TypeParser().match(input);
        if (typeResult.isFailure()) {
            return typeResult;
        }

        Tokens before = typeResult.next();
        matched.addAll(typeResult.matchedF());
        final TypeName typeNode = typeResult.getNode().map(TypeName.class::cast).orElseThrow(IllegalStateException::new);

        final MatchResult identifierResult = new IdentifierParser().match(before);
        if (identifierResult.isFailure()) {
            return identifierResult;
        }

        before = identifierResult.next();
        matched.addAll(identifierResult.matchedF());
        final Name nameNode = identifierResult.getNode().map(Name.class::cast).orElseThrow(IllegalStateException::new);

        final MatchResult matchResult = TokenMatcher.create(Semi.class).match(before);
        if (matchResult.isFailure()) {
            return matchResult;
        }
        matched.addAll(matchResult.matchedF());

        return new MatchResult.Success(matchResult.next(), matched, new VarDef(nameNode, typeNode));
    }

}
