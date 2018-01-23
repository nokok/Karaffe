package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.CommonToken.Colon;
import org.karaffe.compiler.lexer.CommonToken.Semi;
import org.karaffe.compiler.lexer.KeywordToken.Let;
import org.karaffe.compiler.lexer.KeywordToken.Var;
import org.karaffe.compiler.lexer.OperatorToken.Equals;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Node;

public class VarDefParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        CParser cp = new CParser(input);
        if (cp.testNext(TokenMatcher.create(Let.class))) {
            // ValDef := 'let' Identifier (: TypeName)? = Expr (;)?
            if (!cp.testNext(new IdentifierParser())) {
                return cp.toFailure();
            }
            Node name = cp.lastMatch();
            Node typeName;
            if (cp.testNext(TokenMatcher.create(Colon.class)) && cp.testNext(new TypeParser())) {
                typeName = cp.lastMatch();
            } else {
                typeName = new Empty();
            }

            if (!cp.testNext(TokenMatcher.create(Equals.class))) {
                return cp.toFailure();
            }

            if (!cp.testNext(new ExprParser())) {
                return cp.toFailure();
            }

            Node expr = cp.lastMatch();
            cp.testNext(TokenMatcher.create(Semi.class));

            // Type
            return new MatchResult.Success(cp.next(), cp.matched(), new ValDef(new Modifiers(), name, typeName, expr));
        } else if (cp.testNext(TokenMatcher.create(Var.class))) {
            // VarDef := 'var' Identifier (: TypeName)? (= Expr)? (;)?
            if (!cp.testNext(new IdentifierParser())) {
                return cp.toFailure();
            }
            Node name = cp.lastMatch();
            Node typeName;
            if (cp.testNext(TokenMatcher.create(Colon.class)) && cp.testNext(new TypeParser())) {
                typeName = cp.lastMatch();
            } else {
                typeName = new Empty();
            }

            Node expr = null;
            if (cp.testNext(TokenMatcher.create(Equals.class)) && cp.testNext(new ExprParser())) {
                expr = cp.lastMatch();
            }
            cp.testNext(TokenMatcher.create(Semi.class));

            if (expr == null) {
                return new MatchResult.Success(cp.next(), cp.matched(), new VarDef(new Modifiers(), name, typeName));
            }
            // Type
            return new MatchResult.Success(cp.next(), cp.matched(), new VarDef(new Modifiers(), name, typeName, expr));
        }
        return cp.toFailure();
        // if (!cp.testNext(new TypeParser())) {
        // return cp.toFailure();
        // }
        // Node typeNode = cp.lastMatch();
        // if (!cp.testNext(new IdentifierParser())) {
        // return cp.toFailure();
        // }
        // Node nameNode = cp.lastMatch();
        //
        // if (!cp.testNext(Semi.class)) {
        // return cp.chain(nodes -> {
        // Node initializer = nodes.get(1);
        // return new VarDef(new Modifiers(), (Name) nameNode, (TypeName) typeNode, initializer);
        // },
        // Action.of(Equals.class),
        // Action.of(new ExprParser()), // 1
        // Action.of(Semi.class));
        // }
        // return new MatchResult.Success(cp.next(), cp.matched(), new VarDef(new Modifiers(),
        // (Name) nameNode, (TypeName) typeNode));
    }

}
