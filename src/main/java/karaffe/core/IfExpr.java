package karaffe.core;

import java.util.function.Supplier;

public class IfExpr {

    public static <R> R get(Boolean condition, Supplier<R> func1, Supplier<R> func2) {
        if ( condition ) {
            return func1.get();
        } else {
            return func2.get();
        }
    }
}
