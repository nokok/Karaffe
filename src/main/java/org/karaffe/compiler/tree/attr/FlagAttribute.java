package org.karaffe.compiler.tree.attr;

public abstract class FlagAttribute extends Attribute {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}