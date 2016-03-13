/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase;

import java.util.function.Function;

public abstract class Phase<T, R> implements Function<T, R> {

    private final String phaseName;

    public Phase(String phaseName) {
        this.phaseName = phaseName;
    }

    public String phaseName() {
        return phaseName;
    }
}
