package org.karaffe.compiler.base.tree.modifiers;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

class ModifierImpl extends AbstractTree implements Modifier {
    private final ModifierType modifierType;

    ModifierImpl(Tree parent, ModifierType modifierType) {
        super(parent, TreeKind.MODIFIER);
        this.modifierType = modifierType;
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    @Override
    public int modifierVal() {
        return this.modifierType.ordinal();
    }

    @Override
    public int compareTo(Modifier modifier) {
        return 0;
    }
}
