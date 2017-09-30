package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.IdentifierToken;

public class VarName extends TermNode {

    public VarName(final IdentifierToken token) {
        super(NodeType.VARNAME, token);
    }
}
