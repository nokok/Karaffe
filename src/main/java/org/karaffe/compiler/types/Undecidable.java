package org.karaffe.compiler.types;

public abstract class Undecidable implements InferResult {

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isFailed() {
        return false;
    }

    @Override
    public String toString() {
        return "?";
    }
}
