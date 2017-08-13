package org.karaffe.compiler.util;

import java.util.function.BiFunction;

public class Tuple2<L, R> {
    private final L left;
    private final R right;

    public Tuple2(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    public <U> U extract(final BiFunction<L, R, U> f) {
        return f.apply(this.left, this.right);
    }

    @Override
    public String toString() {
        return String.format("Tuple2{%s, %s}", this.left, this.right);
    }
}
