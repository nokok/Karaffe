package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.TypeName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        TypeParser.LOGGER.debug("Input : {}", input);
        final MatchResult intArrayResult = TokenMatcher.create(KeywordToken.Int.class, OperatorToken.LeftBracket.class, OperatorToken.RightBracket.class).match(input);

        if (intArrayResult.isSuccess()) {
            final TypeName typeName = TypeName.createArrayType("int");
            return new MatchResult.Success(intArrayResult.next(), intArrayResult.matchedF(), typeName);
        }

        final MatchResult arrayResult = TokenMatcher.create(IdentifierToken.class, OperatorToken.LeftBracket.class, OperatorToken.RightBracket.class).match(input);

        if (arrayResult.isSuccess()) {
            final Token typeNameToken = arrayResult.matchedF()
                    .stream()
                    .filter(t -> t.is(IdentifierToken.class))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);

            final TypeName typeName = TypeName.createArrayType(typeNameToken.getText());
            return new MatchResult.Success(arrayResult.next(), arrayResult.matchedF(), typeName);
        }

        final MatchResult intTypeResult = TokenMatcher.create(KeywordToken.Int.class).match(input);

        if (intTypeResult.isSuccess()) {
            final TypeName typeName = TypeName.createType("int");
            return new MatchResult.Success(intTypeResult.next(), intTypeResult.matchedF(), typeName);
        }

        final MatchResult booleanTypeResult = TokenMatcher.create(KeywordToken.Boolean.class).match(input);

        if (booleanTypeResult.isSuccess()) {
            final TypeName typeName = TypeName.createType("boolean");
            return new MatchResult.Success(booleanTypeResult.next(), booleanTypeResult.matchedF(), typeName);
        }

        final MatchResult typeNameResult = TokenMatcher.identifier().match(input);
        if (typeNameResult.isSuccess()) {
            final Token typeNameToken = typeNameResult.matchedF()
                    .stream()
                    .filter(t -> t.is(IdentifierToken.class))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);

            final TypeName typeName = TypeName.createType(typeNameToken.getText());
            return new MatchResult.Success(typeNameResult.next(), typeNameResult.matchedF(), typeName);
        }
        return new MatchResult.Failure(input);
    }

}
