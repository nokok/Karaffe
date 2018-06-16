package org.karaffe.compiler.base.tree.modifier;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

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
        case SYNTHETIC:
            return visitor.visitSyntheticMod(this, p);
        }
        throw new IllegalStateException();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleModifier that = (SimpleModifier) o;
        return modifierType == that.modifierType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), modifierType);
    }

    @Override
    public String toString() {
        return this.modifierType.toString();
    }
}
