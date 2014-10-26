package net.nokok.karaffe.javacc.type;

public class Tuple<T1, T2> {

    private final T1 a;
    private final T2 b;

    public Tuple(T1 a, T2 b) {
        this.a = a;
        this.b = b;
    }

    public T1 get1() {
        return a;
    }

    public T2 get2() {
        return b;
    }

}
