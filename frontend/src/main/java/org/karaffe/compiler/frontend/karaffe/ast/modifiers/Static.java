package org.karaffe.compiler.frontend.karaffe.ast.modifiers;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.Modifier;

public class Static extends AbstractTree implements Modifier {

    public Static() {

    }

    public Static(Position position) {
        super(position);
    }

    @Override
    public int getJvmModifierValue() {
        return java.lang.reflect.Modifier.STATIC;
    }

    @Override
    public String toString() {
        return "static";
    }

}
