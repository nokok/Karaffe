package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.Tree;

public interface Terms {
    static Path emptyName() {
        return new EmptyPath();
    }

    static Path moduleName(String name) {
        return new DotSeparatedPath(name, NameKind.MODULE);
    }

    static Path packageName(String name) {
        return new DotSeparatedPath(name, NameKind.PACKAGE);
    }

    static Path arrayTypeName(Path elementName) {
        return new NestedPath(typeName("Array"), elementName);
    }

    static Path typeName(String name) {
        return new DotSeparatedPath(name, NameKind.TYPENAME);
    }

    static Path varName(String name) {
        return new SimplePath(name, NameKind.VARNAME);
    }

    static Path thisName() {
        return new SimplePath("this", NameKind.THIS);
    }

    static Tree emptyTree() {
        return new EmptyTree();
    }

    static Path primitiveVoid() {
        return new SimplePath("void", NameKind.TYPENAME, true);
    }
}
