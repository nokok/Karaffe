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
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
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
        final CParser cp = new CParser(input);
        if (!cp.testNext(Public.class)) {
            return cp.toFailure();
        }
        if (!cp.testNext(new TypeParser())) {
            return cp.toFailure();
        }
        final org.karaffe.compiler.tree.TypeName returnType = cp.lastMatch();
        if (!cp.testNext(new IdentifierParser())) {
            return cp.toFailure();
        }
        final Name name = cp.lastMatch();
        if (!cp.testNext(LeftParen.class)) {
            return cp.toFailure();
        }
        final boolean hasParameter = cp.testNext(new FormalListParser());

        final Node params = hasParameter ? cp.lastMatch() : new Empty();

        if (!cp.testNext(RightParen.class)) {
            return cp.toFailure();
        }
        if (!cp.testNext(LeftBrace.class)) {
            return cp.toFailure();
        }
        final List<Node> stmts = new ArrayList<>();
        while (cp.testNext(new VarDefParser())) {
            stmts.add(cp.lastMatch());
        }
        while (cp.testNext(new StatementParser())) {
            stmts.add(cp.lastMatch());
        }
        if (!cp.testNext(Return.class)) {
            return cp.toFailure();
        }
        if (!cp.testNext(new ExprParser())) {
            return cp.toFailure();
        }
        stmts.add(cp.lastMatch());
        if (!cp.testNext(Semi.class)) {
            return cp.toFailure();
        }
        if (!cp.testNext(RightBrace.class)) {
            return cp.toFailure();
        }

        final Modifiers modifiers = new Modifiers(new Modifier(new ModifierToken.Public()));
        final MethodDef methodDef = new MethodDef(modifiers, name, params, returnType, new Block(stmts));
        return new MatchResult.Success(cp.next(), cp.matched(), methodDef);
    }

}
