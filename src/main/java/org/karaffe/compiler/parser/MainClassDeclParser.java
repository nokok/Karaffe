package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.lexer.CommonToken.LeftBrace;
import org.karaffe.compiler.lexer.CommonToken.LeftParen;
import org.karaffe.compiler.lexer.CommonToken.RightBrace;
import org.karaffe.compiler.lexer.CommonToken.RightParen;
import org.karaffe.compiler.lexer.KeywordToken.Void;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.ModifierToken.Static;
import org.karaffe.compiler.parser.util.CParser;
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
        final CParser cp = new CParser(input);
        if (!cp.testNext(TokenMatcher.classKeyword())) {
            return cp.toFailure();
        }

        if (!cp.testNext(TokenMatcher.identifier())) {
            return cp.toFailure();
        }
        final Node mainClassName = cp.lastMatch();

        boolean validDef = cp.testNext(LeftBrace.class);
        validDef |= cp.testNext(Public.class);
        validDef |= cp.testNext(Static.class);
        validDef |= cp.testNext(Void.class);

        if (!validDef) {
            return cp.toFailure();
        }

        if (!cp.testNext(TokenMatcher.identifier())) {
            return cp.toFailure();
        }
        final Node mainMethod = cp.lastMatch();

        if (!cp.testNext(LeftParen.class)) {
            return cp.toFailure();
        }

        if (!cp.testNext(new TypeParser())) {
            return cp.toFailure();
        }
        final Node argType = cp.lastMatch();

        if (!cp.testNext(TokenMatcher.identifier())) {
            return cp.toFailure();
        }
        final Name argName = cp.lastMatch();

        if (!cp.testNext(TokenMatcher.create(RightParen.class)) | !cp.testNext(TokenMatcher.create(LeftBrace.class))) {
            return cp.toFailure();
        }

        if (!cp.testNext(new StatementParser())) {
            return cp.toFailure();
        }
        final Node statement = cp.lastMatch();

        if (!cp.testNext(TokenMatcher.create(RightBrace.class)) | !cp.testNext(TokenMatcher.create(RightBrace.class))) {
            return cp.toFailure();
        }

        final List<Node> stmt = new ArrayList<>(1);
        stmt.add(statement);

        final Parameters parameters = new Parameters(new ValDef(new Modifiers(), argName, (TypeName) argType));
        final MethodDef methodDef = new MethodDef(new Modifiers(), mainMethod, parameters, new TypeName(new IdentifierToken.TypeName("void"), false), new Block(statement));

        final List<Node> methodDefs = new ArrayList<>();
        methodDefs.add(methodDef);
        final ClassDef classDef = new ClassDef(mainClassName, new Name(new IdentifierToken.TypeName("Object")), new Block(methodDefs));
        return new MatchResult.Success(cp.next(), cp.matched(), classDef);
    }

}
