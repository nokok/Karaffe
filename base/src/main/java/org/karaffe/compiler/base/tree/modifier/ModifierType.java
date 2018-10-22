package org.karaffe.compiler.base.tree.modifier;

public enum ModifierType {
    PUBLIC("public"),
    SYNTHETIC("synthetic"),
    STATIC("static"),
    ;

    private final String string;

    ModifierType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
