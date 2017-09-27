package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.KeywordToken.Return;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.LeftParen;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightParen;
import org.karaffe.compiler.lexer.OperatorToken.Semi;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Modifier;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Parameters;
import org.karaffe.compiler.tree.Statement;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Name;

public class MethodDefParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final List<Token> matched = new ArrayList<>();
        final MatchResult modifierMatch = TokenMatcher.create(Public.class).match(input);
        if (modifierMatch.isFailure()) {
            return new MatchResult.Failure(modifierMatch.erroredHead().orElse(input.get(0)), input);
        }

        Tokens before = modifierMatch.next();
        matched.addAll(modifierMatch.matchedF());

        final MatchResult returnTypeMatch = new TypeParser().match(before);

        if (returnTypeMatch.isFailure()) {
            return new MatchResult.Failure(returnTypeMatch.erroredHead().orElse(input.get(0)), input);
        }

        before = returnTypeMatch.next();
        matched.addAll(returnTypeMatch.matchedF());

        final MatchResult methodNameMatch = new IdentifierParser().match(before);
        if (methodNameMatch.isFailure()) {
            return new MatchResult.Failure(methodNameMatch.erroredHead().orElse(input.get(0)), input);
        }

        before = methodNameMatch.next();
        matched.addAll(methodNameMatch.matchedF());

        final MatchResult paramStart = TokenMatcher.create(LeftParen.class).match(before);
        if (paramStart.isFailure()) {
            return new MatchResult.Failure(paramStart.erroredHead().orElse(input.get(0)), input);
        }
        before = paramStart.next();
        matched.addAll(paramStart.matchedF());

        final MatchResult formalParameter = new FormalListParser().match(before);
        if (formalParameter.isFailure() && formalParameter.next().size() != before.size()) {
            return new MatchResult.Failure(formalParameter.erroredHead().orElse(input.get(0)), input);
        }

        before = formalParameter.next();
        matched.addAll(formalParameter.matchedF());

        final MatchResult paramEnd = TokenMatcher.create(RightParen.class).match(before);
        if (paramEnd.isFailure()) {
            return new MatchResult.Failure(paramEnd.erroredHead().orElse(input.get(0)), input);
        }
        before = paramEnd.next();
        matched.addAll(paramEnd.matchedF());

        final MatchResult bodyStart = TokenMatcher.create(LeftBrace.class).match(before);
        if (bodyStart.isFailure()) {
            return new MatchResult.Failure(bodyStart.erroredHead().orElse(input.get(0)), input);
        }
        before = bodyStart.next();
        matched.addAll(bodyStart.matchedF());

        final List<VarDef> varDefs = new ArrayList<>();
        MatchResult varDefLastMatch = null;
        do {
            final MatchResult varDefMatch = new VarDefParser().match(before);
            if (varDefLastMatch == null) {
                varDefLastMatch = varDefMatch;
            }
            if (varDefMatch.isFailure()) {
                break;
            }
            before = varDefMatch.next();
            matched.addAll(varDefMatch.matchedF());
            varDefs.add(varDefMatch.getNode().map(VarDef.class::cast).get());
        } while (varDefLastMatch.isSuccess());

        final List<Statement> statements = new ArrayList<>();
        MatchResult stmtLastMatch = null;
        do {
            final MatchResult stmts = new StatementParser().match(before);
            if (stmtLastMatch == null) {
                stmtLastMatch = stmts;
            }
            if (stmts.isFailure()) {
                break;
            }
            before = stmts.next();
            matched.addAll(stmts.matchedF());
            // statements.add(stmts.getNode().map(Statement.class::cast).get());
        } while (stmtLastMatch.isSuccess());

        final MatchResult returnMatch = TokenMatcher.create(Return.class).match(before);

        if (returnMatch.isFailure()) {
            return new MatchResult.Failure(returnMatch.erroredHead().orElse(input.get(0)), input);
        }
        before = returnMatch.next();

        final MatchResult returnExprMatch = new ExprParser().match(before);
        if (returnExprMatch.isFailure()) {
            return new MatchResult.Failure(returnExprMatch.erroredHead().orElse(input.get(0)), input);
        }
        before = returnExprMatch.next();
        matched.addAll(returnExprMatch.matchedF());

        final MatchResult semiMatch = TokenMatcher.create(Semi.class).match(before);

        if (semiMatch.isFailure()) {
            return new MatchResult.Failure(semiMatch.erroredHead().orElse(input.get(0)), input);
        }
        before = semiMatch.next();

        final MatchResult bodyEnd = TokenMatcher.create(RightBrace.class).match(before);
        if (bodyEnd.isFailure()) {
            return new MatchResult.Failure(bodyEnd.erroredHead().orElse(input.get(0)), input);
        }

        final Token modifierToken = modifierMatch.matchedF().get(0);
        final Modifiers methodModifiers = new Modifiers(new Modifier(modifierToken));

        final Name methodName = methodNameMatch.getNode().map(Name.class::cast).orElseThrow(IllegalStateException::new);

        final Parameters methodParameters = formalParameter.getNode().map(Parameters.class::cast).orElseGet(Parameters::new);

        final TypeName returnType = returnTypeMatch.getNode().map(TypeName.class::cast).orElseThrow(IllegalStateException::new);

        final MethodDef methodDef = new MethodDef(methodModifiers, methodName, methodParameters, returnType, varDefs, statements);
        return new MatchResult.Success(bodyEnd.next(), matched, methodDef);
    }

}
