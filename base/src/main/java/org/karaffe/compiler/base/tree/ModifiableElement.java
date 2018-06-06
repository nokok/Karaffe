package org.karaffe.compiler.base.tree;

import java.util.List;

public interface ModifiableElement {
    List<Tree> getModifiers();

    void addModifier(Tree modifier);

    void setModifiers(List<Tree> modifiers);

    void clearModifiers();
}
