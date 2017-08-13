package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.NodeD;

public class Argument implements NodeD {
    private final Token type;
    private final Token name;

    public Argument(final Token type, final Token name) {
        this.type = type;
        this.name = name;
    }

    public Token getType() {
        return type;
    }

    public Token getName() {
        return name;
    }
}
