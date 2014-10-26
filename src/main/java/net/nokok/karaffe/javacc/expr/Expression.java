package net.nokok.karaffe.javacc.expr;

public interface Expression<T, R> {

    public R eval(T arg);
}
