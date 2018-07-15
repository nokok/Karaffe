package org.karaffe.compiler.base.mir.util.attr;

import org.karaffe.compiler.base.tree.modifier.Modifier;

public class ModifierAttribute extends Attribute {
    private Modifier modifier;

    public ModifierAttribute(Modifier modifierVal) {
        this.modifier = modifierVal;
    }

    public Modifier getModifier() {
        return modifier;
    }

    @Override
    public String toString() {
        return modifier.getType().toString();
    }
}
