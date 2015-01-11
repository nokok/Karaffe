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

public class Either<X extends Exception, T> {

    private final X left;
    private final T right;

    public Either(X left, T right) {
        this.left = left;
        this.right = right;
    }

    public X getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }
}
