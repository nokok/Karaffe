package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KeywordToken.Void;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.ModifierToken.Static;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.LeftParen;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightParen;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Statement;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.VarName;

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

        final Optional<VarName> mainClassName = parser.nextMatch(TokenMatcher.identifier(), VarName.class);
        if (!mainClassName.isPresent()) {
            return parser.toFailure();
        }

        boolean validDef = parser.testNext(TokenMatcher.create(LeftBrace.class));
        validDef |= parser.testNext(TokenMatcher.create(Public.class));
        validDef |= parser.testNext(TokenMatcher.create(Static.class));
        validDef |= parser.testNext(TokenMatcher.create(Void.class));

        if (!validDef) {
            return parser.toFailure();
        }

        final Optional<VarName> mainMethod = parser.nextMatch(TokenMatcher.identifier(), VarName.class);
        if (!mainMethod.isPresent()) {
            return parser.toFailure();
        }

        if (!parser.testNext(TokenMatcher.create(LeftParen.class))) {
            return parser.toFailure();
        }

        final Optional<TypeName> returnTypeName = parser.nextMatch(new TypeParser(), TypeName.class);
        if (!returnTypeName.isPresent()) {
            return parser.toFailure();
        }

        final Optional<VarName> argName = parser.nextMatch(TokenMatcher.identifier(), VarName.class);
        if (!argName.isPresent()) {
            return parser.toFailure();
        }

        if (!parser.testNext(TokenMatcher.create(RightParen.class)) | !parser.testNext(TokenMatcher.create(LeftBrace.class))) {
            return parser.toFailure();
        }

        final Optional<Statement> statement = parser.nextMatch(new StatementParser(), Statement.class);
        if (!statement.isPresent()) {
            return parser.toFailure();
        }

        if (!parser.testNext(TokenMatcher.create(RightBrace.class)) | !parser.testNext(TokenMatcher.create(RightBrace.class))) {
            return parser.toFailure();
        }

        final List<Statement> stmt = new ArrayList<>(1);
        statement.ifPresent(stmt::add);

        final Modifiers modifiers = new Modifiers();
        final MethodDef methodDef = new MethodDef(modifiers, new VarName(new IdentifierToken.VarName("main")), parameters, new org.karaffe.compiler.tree.TypeName(new IdentifierToken.TypeName("void"), false), new ArrayList<>(0), stmt);

        final List<MethodDef> methodDefs = new ArrayList<>();
        methodDefs.add(methodDef);
        final ClassDef classDef = new ClassDef(mainClassName.orElseThrow(IllegalStateException::new), new VarName(new IdentifierToken.TypeName("Object")), new ArrayList<>(), methodDefs);
        return new MatchResult.Success(parser.next(), parser.matched(), classDef);
    }

}
