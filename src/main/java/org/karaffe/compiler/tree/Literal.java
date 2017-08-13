package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;

public abstract class Literal<T> extends SimpleNode {
    protected T value;

    protected Literal(final Token token, final T value) {
        super(token);
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public static class Int extends Literal<Integer> {
        protected Int(final Token token) {
            super(token, Integer.parseInt(token.getText()));
        }
    }

}
