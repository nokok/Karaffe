/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase;

import java.util.function.Supplier;
import karaffe.core.Either;

public abstract class Phase<T, R> implements Supplier<Either<Exception, R>> {

    protected final T obj;

    public Phase(T obj) {
        this.obj = obj;
    }
}
