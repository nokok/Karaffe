/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / ,\ / __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package karaffe.lang;

import java.util.Objects;
import karaffe.lang.internal.KaraffeJavaBox;

public class Int extends Any {

    private final int value;

    public Int() {
        this(0);
    }

    public Int(Int value) {
        this(KaraffeJavaBox.unbox(value));
    }

    public Int(int value) {
        this.value = value;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(value);
    }

    @Override
    public String hash() {
        return string().hash();
    }

    @Override
    public String string() {
        return new String();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }
}
