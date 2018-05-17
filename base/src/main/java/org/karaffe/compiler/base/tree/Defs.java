package org.karaffe.compiler.base.tree;

public interface Defs extends Tree {

    static ClassDef Class(String name) {
        return new ClassDef(name);
    }
}
