package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.KeywordToken.False;
import org.karaffe.compiler.lexer.KeywordToken.True;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.KNormalizable;

public class Literal extends TermNode implements KNormalizable {

    public Literal(final Token token) {
        super(NodeType.LITERAL, token);
    }

    @Override
    public NodeList normalize() {
        Token token = this.getToken();
        if (token.is(True.class)) {
            return new Literal(new LiteralToken.IntLiteral("1", token.getPosition())).toNodeList();
        } else if (token.is(False.class)) {
            return new Literal(new LiteralToken.IntLiteral("0", token.getPosition())).toNodeList();
        }
        return this.toNodeList();
    }

}
