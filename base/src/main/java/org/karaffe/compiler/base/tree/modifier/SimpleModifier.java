package org.karaffe.compiler.base.tree.modifier;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

class SimpleModifier extends AbstractTree implements Modifier {
    private final ModifierType modifierType;

    SimpleModifier(Tree parent, ModifierType modifierType) {
        super(parent, TreeKind.MODIFIER);
        this.modifierType = modifierType;
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        switch (this.modifierType) {
        case PUBLIC:
            return visitor.visitPublicMod(this, p);
        case STATIC:
            return visitor.visitStaticMod(this, p);
        }
        return visitor.visit(this, p);
    }

    @Override
    public int modifierVal() {
        return this.modifierType.ordinal();
    }

    @Override
    public ModifierType getType() {
        return this.modifierType;
    }

    @Override
    public int compareTo(Modifier modifier) {
        return 0;
    }
}
