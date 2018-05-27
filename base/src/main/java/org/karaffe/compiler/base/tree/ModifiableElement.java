package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.modifier.Modifier;

import java.util.Set;

public interface ModifiableElement {
    Set<Modifier> getModifiers();

    void addModifier(Modifier modifier);

    void clearModifiers();
}
