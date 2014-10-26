package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.expr.*;

public interface Literal<T> extends Expression<Void, T> {

    public LiteralType getType();

}
