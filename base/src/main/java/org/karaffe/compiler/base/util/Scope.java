package org.karaffe.compiler.base.util;

public class Scope {

    private final Scope parent;

    public Scope() {
        this(null);
    }

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Scope newScope() {
        return new Scope(this);
    }

}
