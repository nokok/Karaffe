package org.karaffe.compiler.base.attr;

public abstract class FlagAttribute extends Attribute {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
