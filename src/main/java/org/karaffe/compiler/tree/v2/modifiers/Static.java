package org.karaffe.compiler.tree.v2.modifiers;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Modifier;
import org.karaffe.compiler.tree.v2.api.TreeVisitor;

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

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visit(this);
    }
}
