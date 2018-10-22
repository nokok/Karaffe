package org.karaffe.compiler.base.tree.modifier;

import org.karaffe.compiler.base.tree.TreeVisitor;

public class PublicModifier extends AbstractModifier implements Modifier {

    @Override
    public ModifierType getType() {
        return ModifierType.PUBLIC;
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
