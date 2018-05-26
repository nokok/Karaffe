package org.karaffe.compiler.base.tree.term;

public interface Terms {
    static Name emptyName() {
        return NameImpl.EMPTY_NAME;
    }

    static Name packageName(String name) {
        return new NameImpl(name, NameType.PACKAGE);
    }

    static Name fqcn(String name) {
        return new NameImpl(name, NameType.FQCN);
    }

    static Name typeName(String name) {
        return new NameImpl(name, NameType.TYPENAME);
    }

    static Name varName(String name) {
        return new NameImpl(name, NameType.VARNAME);
    }
}
