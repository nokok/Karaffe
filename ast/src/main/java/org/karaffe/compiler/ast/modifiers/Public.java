package org.karaffe.compiler.ast.modifiers;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.api.AbstractTree;
import org.karaffe.compiler.ast.api.Modifier;

public class Public extends AbstractTree implements Modifier {
    public Public() {

    }

    public Public(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "public";
    }

    @Override
    public int getJvmModifierValue() {
        return java.lang.reflect.Modifier.PUBLIC;
    }

}
