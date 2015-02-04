/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.typechecker;

public class Inferer {

    private final Object object;

    public Inferer(Object object) {
        this.object = object;
    }

    public Class<?> inferredClass() {
        return object.getClass();
    }
}
