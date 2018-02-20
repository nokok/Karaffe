package org.karaffe.compiler.tree.v2.modifiers;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Modifier;

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
