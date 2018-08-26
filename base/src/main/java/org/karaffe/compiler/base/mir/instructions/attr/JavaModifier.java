package org.karaffe.compiler.base.mir.instructions.attr;

import org.karaffe.compiler.base.attr.Attribute;
import org.karaffe.compiler.base.tree.modifier.Modifier;

public class JavaModifier extends Attribute {
    private Modifier modifier;

    public JavaModifier(Modifier modifierVal) {
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
