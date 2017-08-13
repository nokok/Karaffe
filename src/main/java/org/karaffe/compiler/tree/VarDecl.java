package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.NodeD;

public class VarDecl implements NodeD {

    private final Token identifier;
    private final Token type;

    public VarDecl(final Token identifier, final Token type) {
        this.identifier = identifier;
        this.type = type;
    }

    public Token getIdentifier() {
        return this.identifier;
    }

    public Token getType() {
        return this.type;
    }

}
