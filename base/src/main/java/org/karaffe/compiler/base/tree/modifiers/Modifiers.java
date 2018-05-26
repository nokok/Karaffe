package org.karaffe.compiler.base.tree.modifiers;

import org.karaffe.compiler.base.tree.Tree;

public interface Modifiers {
    static Modifier modPublic(Tree parent) {
        return new ModifierImpl(parent, ModifierType.PUBLIC);
    }

    static Modifier modStatic(Tree parent) {
        return new ModifierImpl(parent, ModifierType.STATIC);
    }
}
