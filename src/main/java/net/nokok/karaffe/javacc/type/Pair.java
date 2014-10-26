package net.nokok.karaffe.javacc.type;

public class Pair<T> {

    private final Tuple<T, T> tuple;

    public Pair(T a, T b) {
        this.tuple = new Tuple<>(a, b);
    }

    public T getA() {
        return tuple.get1();
    }

    public T getB() {
        return tuple.get2();
    }
}
