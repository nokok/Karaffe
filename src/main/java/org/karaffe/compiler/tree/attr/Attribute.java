package org.karaffe.compiler.tree.attr;

public abstract class Attribute {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
