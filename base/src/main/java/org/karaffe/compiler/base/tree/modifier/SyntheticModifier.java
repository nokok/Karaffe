package org.karaffe.compiler.base.tree.modifier;

import org.karaffe.compiler.base.tree.TreeVisitor;

public class SyntheticModifier extends AbstractModifier {
    @Override
    public ModifierType getType() {
        return ModifierType.SYNTHETIC;
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
