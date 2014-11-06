/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / ,< / __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package karaffe.lang.internal;

import karaffe.lang.Int;

public final class KaraffeJavaBox {

    public static Integer unbox(Int value) {
        return Integer.valueOf(value.toString());
    }

    public static Int box(Integer value) {
        return new Int(value);
    }
}
