package org.karaffe.compiler.tree.attr;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModifierAttribute extends Attribute {
    private List<JavaModifier> modifiers;

    public ModifierAttribute(JavaModifier... modifiers) {
        this.modifiers = Arrays.asList(modifiers);
    }

    public void add(JavaModifier modifier) {
        this.modifiers.add(Objects.requireNonNull(modifier));
    }

    @Override
    public String toString() {
        return String.format("ModifierAttribute%s", this.modifiers.isEmpty() ? "" : "=" + this.modifiers);
    }
}
