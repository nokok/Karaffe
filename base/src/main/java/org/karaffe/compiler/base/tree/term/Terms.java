package org.karaffe.compiler.base.tree.term;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.type.Array;
import org.karaffe.compiler.base.tree.type.primitive.Void;

public interface Terms {
    static Tree emptyName() {
        return new EmptyTree();
    }

    static Tree moduleName(Position position, String name) {
        return null;
    }

    static Tree packageName(Position position, String name) {
        return null;
    }

    static Tree arrayTypeName(Position position, Tree elementTypeName) {
        Array array = new Array(elementTypeName);
        array.setPos(position);
        return array;
    }

    static Tree typeName(String typeName) {
        return null;
    }

    static Tree typeName(Token token) {
        return typeName(Position.of(token), token.getText());
    }

    static Tree typeName(Position position, String name) {
        TypeName typeName = new TypeName(name);
        typeName.setPos(position);
        return typeName;
    }

    static Tree varName(Token token) {
        return varName(Position.of(token), token.getText());
    }

    static Tree varName(Position position, String name) {
        VarName varName = new VarName(name);
        varName.setPos(position);
        return varName;
    }

    static Tree thisName(Position position) {
        return null;
    }

    static Tree superName(Position position) {
        return null;
    }

    static Tree emptyTree() {
        return null;
    }

    static Tree primitiveVoid(Position position) {
        Void v = new Void();
        v.setPos(position);
        return v;
    }
}
