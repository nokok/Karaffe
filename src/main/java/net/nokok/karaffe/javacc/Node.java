package net.nokok.karaffe.javacc;

import net.nokok.karaffe.javacc.stmt.*;

public interface Node<T> {

    public T getType();

    public Object accept(StatementListener listener);
}
