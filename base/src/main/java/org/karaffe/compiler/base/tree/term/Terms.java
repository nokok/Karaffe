package org.karaffe.compiler.base.tree.term;

public interface Terms {
    static Name emptyName() {
        return SimpleName.EMPTY_NAME;
    }

    static Name packageName(String name) {
        return new SimpleName(name, NameType.PACKAGE);
    }

    static Name fqcn(String name) {
        return new SimpleName(name, NameType.FQCN);
    }

    static Name typeName(String name) {
        return new SimpleName(name, NameType.TYPENAME);
    }

    static Name varName(String name) {
        return new SimpleName(name, NameType.VARNAME);
    }

    static Name specialName(String name) {
        return new SimpleName(name, NameType.NONE);
    }

    static Name thisName() {
        return new SimpleName("this", NameType.THIS);
    }

}
