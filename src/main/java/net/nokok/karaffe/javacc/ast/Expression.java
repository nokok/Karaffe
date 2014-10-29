package net.nokok.karaffe.javacc.ast;

public abstract class Expression<T, R> implements ASTNode {

    public abstract R eval(T obj);

}
