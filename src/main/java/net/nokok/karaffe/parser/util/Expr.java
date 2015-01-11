/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import java.util.function.Function;

public class Expr<T> {

    private final T value;

    public Expr(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public T eval() {
        return value;
    }

    public <R> Expr<R> eval(Function<Expr<T>, Expr<R>> func, Expr<T> arg) {
        return func.apply(arg);
    }

    public Class<T> getType() {
        return (Class<T>) value.getClass();
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
