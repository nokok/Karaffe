package net.nokok.karaffe.javacc.ast;

public abstract class Literal<T> extends Expression<Void, T> {

    protected final T value;

    public Literal(T value) {
        this.value = value;
    }

    @Override
    public T eval(Void obj) {
        return value;
    }
}
