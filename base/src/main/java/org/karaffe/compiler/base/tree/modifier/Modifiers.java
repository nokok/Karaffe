package org.karaffe.compiler.base.tree.modifier;

import org.karaffe.compiler.base.tree.Tree;

public interface Modifiers {
    static Modifier modPublic(Tree parent) {
        return new SimpleModifier(parent, ModifierType.PUBLIC);
    }

    static Modifier modStatic(Tree parent) {
        return new SimpleModifier(parent, ModifierType.STATIC);
    }

    static Tree modSynthetic(Tree parent) {
        return new SimpleModifier(parent, ModifierType.SYNTHETIC);
    }
}
