package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.CommonToken.LeftBrace;
import org.karaffe.compiler.lexer.CommonToken.LeftParen;
import org.karaffe.compiler.lexer.CommonToken.RightBrace;
import org.karaffe.compiler.lexer.CommonToken.RightParen;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken.Void;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.ModifierToken.Static;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.Parameters;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.base.Node;

public class MainClassDeclParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        final ChainParser parser = new ChainParser(input);
        if (!parser.testNext(TokenMatcher.classKeyword())) {
            return parser.toFailure();
        }

        if (!parser.nextMatch(TokenMatcher.identifier())) {
            return parser.toFailure();
        }
        final Node mainClassName = parser.lastMatch();

        boolean validDef = parser.testNext(TokenMatcher.create(LeftBrace.class));
        validDef |= parser.testNext(TokenMatcher.create(Public.class));
        validDef |= parser.testNext(TokenMatcher.create(Static.class));
        validDef |= parser.testNext(TokenMatcher.create(Void.class));

        if (!validDef) {
            return parser.toFailure();
        }

        if (!parser.nextMatch(TokenMatcher.identifier())) {
            return parser.toFailure();
        }
        final Node mainMethod = parser.lastMatch();

        if (!parser.testNext(TokenMatcher.create(LeftParen.class))) {
            return parser.toFailure();
        }

        if (!parser.nextMatch(new TypeParser())) {
            return parser.toFailure();
        }
        final Node argType = parser.lastMatch();

        if (!parser.nextMatch(TokenMatcher.identifier())) {
            return parser.toFailure();
        }
        final Name argName = parser.lastMatch();

        if (!parser.testNext(TokenMatcher.create(RightParen.class)) | !parser.testNext(TokenMatcher.create(LeftBrace.class))) {
            return parser.toFailure();
        }

        if (!parser.nextMatch(new StatementParser())) {
            return parser.toFailure();
        }
        final Node statement = parser.lastMatch();

        if (!parser.testNext(TokenMatcher.create(RightBrace.class)) | !parser.testNext(TokenMatcher.create(RightBrace.class))) {
            return parser.toFailure();
        }

        final List<Node> stmt = new ArrayList<>(1);
        stmt.add(statement);

        final Parameters parameters = new Parameters(new ValDef(new Modifiers(), argName, (TypeName) argType));
        final MethodDef methodDef = new MethodDef(new Modifiers(), mainMethod, parameters, new TypeName(new IdentifierToken.TypeName("void"), false), new Block(statement));

        final List<Node> methodDefs = new ArrayList<>();
        methodDefs.add(methodDef);
        final ClassDef classDef = new ClassDef(mainClassName, new Name(new IdentifierToken.TypeName("Object")), new Block(methodDefs));
        return new MatchResult.Success(parser.next(), parser.matched(), classDef);
    }

}
