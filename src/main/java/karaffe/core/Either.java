/**
 * Karaffe Programming Language
 */
package karaffe.core;

import java.util.Optional;

public class Either<L, R> {

    private final Optional<L> left;
    private final Optional<R> right;

    private Either(L left, R right) {
        this.left = Optional.ofNullable(left);
        this.right = Optional.ofNullable(right);
    }

    public L left() {
        return left.get();
    }

    public R right() {
        return right.get();
    }

    public boolean isLeft() {
        return left.isPresent();
    }

    public boolean isRight() {
        return right.isPresent();
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }
}
