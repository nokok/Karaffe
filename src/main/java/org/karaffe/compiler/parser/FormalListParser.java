package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.CommonToken.Comma;
import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.LetDef;
import org.karaffe.compiler.tree.Modifier;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.Parameters;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.base.Node;

public class FormalListParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final List<Node> parameters = new ArrayList<>();
        final List<Token> matched = new ArrayList<>();

        MatchResult last = null;
        Tokens before = input;
        do {
            final Modifiers modifiers = new Modifiers(new Modifier(new ModifierToken.Parameter()));

            if (last != null) {
                final MatchResult result = TokenMatcher.create(Comma.class).match(before);
                if (result.isFailure()) {
                    break;
                }
                before = result.next();
            }
            final MatchResult typeMatch = new TypeParser().parse(before);

            last = typeMatch;

            if (typeMatch.isFailure()) {
                return typeMatch;
            }
            before = typeMatch.next();
            final TypeName typeName = typeMatch.getNode().map(TypeName.class::cast).orElseThrow(IllegalStateException::new);
            matched.addAll(typeMatch.matchedF());

            final MatchResult idMatch = new IdentifierParser().parse(before);

            if (idMatch.isFailure()) {
                return idMatch;
            }
            last = idMatch;
            before = idMatch.next();
            matched.addAll(idMatch.matchedF());
            final Name name = idMatch.getNode().map(Name.class::cast).orElseThrow(IllegalStateException::new);
            final LetDef val = new LetDef(modifiers, name, typeName);
            parameters.add(val);
        } while (last.isSuccess() && (before.size() > 0));
        if (last == null) {
            throw new IllegalStateException();
        }
        return new MatchResult.Success(last.next(), matched, new Parameters(parameters));
    }

}
