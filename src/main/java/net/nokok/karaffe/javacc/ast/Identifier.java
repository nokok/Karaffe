package net.nokok.karaffe.javacc.ast;

import static java.util.Objects.requireNonNull;

public abstract class Identifier implements ASTNode {

    protected final String name;

    public Identifier(String name) {
        this.name = requireNonNull(name);
    }

    public String getName() {
        return name;
    }
}
