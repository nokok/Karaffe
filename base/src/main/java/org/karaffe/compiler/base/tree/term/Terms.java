package org.karaffe.compiler.base.tree.term;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;

public interface Terms {
    static Path emptyName() {
        return new EmptyPath();
    }

    static Path moduleName(Position position, String name) {
        DotSeparatedPath dotSeparatedPath = new DotSeparatedPath(name, NameKind.MODULE);
        dotSeparatedPath.setPos(position);
        return dotSeparatedPath;
    }

    static Path packageName(Position position, String name) {
        DotSeparatedPath dotSeparatedPath = new DotSeparatedPath(name, NameKind.PACKAGE);
        dotSeparatedPath.setPos(position);
        return dotSeparatedPath;
    }

    static Path arrayTypeName(Position position, Path elementName) {
        NestedPath array = new NestedPath(typeName(position, "Array"), elementName);
        array.setPos(position);
        return array;
    }

    static Path typeName(String typeName) {
        DotSeparatedPath dotSeparatedPath = new DotSeparatedPath(typeName, NameKind.TYPENAME);
        return dotSeparatedPath;
    }

    static Path typeName(Token token) {
        DotSeparatedPath dotSeparatedPath = new DotSeparatedPath(token.getText(), NameKind.TYPENAME);
        dotSeparatedPath.setPos(Position.of(token));
        return dotSeparatedPath;
    }

    static Path typeName(Position position, String name) {
        DotSeparatedPath dotSeparatedPath = new DotSeparatedPath(name, NameKind.TYPENAME);
        dotSeparatedPath.setPos(position);
        return dotSeparatedPath;
    }

    static Path varName(Token token) {
        SimplePath simplePath = new SimplePath(token.getText(), NameKind.VARNAME);
        simplePath.setPos(Position.of(token));
        return simplePath;
    }

    static Path varName(Position position, String name) {
        SimplePath simplePath = new SimplePath(name, NameKind.VARNAME);
        simplePath.setPos(position);
        return simplePath;
    }

    static Path thisName(Position position) {
        SimplePath aThis = new SimplePath("this", NameKind.THIS);
        aThis.setPos(position);
        return aThis;
    }

    static Path superName(Position position) {
        SimplePath aSuper = new SimplePath("super", NameKind.SUPER);
        aSuper.setPos(position);
        return aSuper;
    }

    static Tree emptyTree() {
        EmptyTree emptyTree = new EmptyTree();
        return emptyTree;
    }

    static Path primitiveVoid(Position position) {
        VoidPath voidPath = new VoidPath();
        voidPath.setPos(position);
        return voidPath;
    }
}
