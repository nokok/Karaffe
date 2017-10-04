package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.CommonToken.LeftBrace;
import org.karaffe.compiler.lexer.CommonToken.LeftParen;
import org.karaffe.compiler.lexer.CommonToken.RightBrace;
import org.karaffe.compiler.lexer.CommonToken.RightParen;
import org.karaffe.compiler.lexer.CommonToken.Semi;
import org.karaffe.compiler.lexer.KeywordToken.Return;
import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.lexer.ModifierToken.Public;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.Modifier;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.base.Node;

public class MethodDefParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        if (input.isEmpty()) {
            return new MatchResult.Failure(input);
        }
        final ChainParser cp = new ChainParser(input);
        if (!cp.nextMatch(TokenMatcher.create(Public.class))) {
            return cp.toFailure();
        }
        if (!cp.nextMatch(new TypeParser())) {
            return cp.toFailure();
        }
        final org.karaffe.compiler.tree.TypeName returnType = cp.lastMatch();
        if (!cp.nextMatch(new IdentifierParser())) {
            return cp.toFailure();
        }
        final Name name = cp.lastMatch();
        if (!cp.nextMatch(TokenMatcher.create(LeftParen.class))) {
            return cp.toFailure();
        }
        final boolean hasParameter = cp.testNext(new FormalListParser());

        final Node params = hasParameter ? cp.lastMatch() : new Empty();

        if (!cp.nextMatch(TokenMatcher.create(RightParen.class))) {
            return cp.toFailure();
        }
        if (!cp.nextMatch(TokenMatcher.create(LeftBrace.class))) {
            return cp.toFailure();
        }
        final List<Node> stmts = new ArrayList<>();
        while (cp.testNext(new VarDefParser())) {
            stmts.add(cp.lastMatch());
        }
        while (cp.testNext(new StatementParser())) {
            stmts.add(cp.lastMatch());
        }
        if (!cp.nextMatch(TokenMatcher.create(Return.class))) {
            return cp.toFailure();
        }
        if (!cp.nextMatch(new ExprParser())) {
            return cp.toFailure();
        }
        if (!cp.nextMatch(TokenMatcher.create(Semi.class))) {
            return cp.toFailure();
        }
        if (!cp.nextMatch(TokenMatcher.create(RightBrace.class))) {
            return cp.toFailure();
        }

        final Modifiers modifiers = new Modifiers(new Modifier(new ModifierToken.Public()));
        final MethodDef methodDef = new MethodDef(modifiers, name, params, returnType, new Block(stmts));
        return new MatchResult.Success(cp.next(), cp.matched(), methodDef);
    }

}
