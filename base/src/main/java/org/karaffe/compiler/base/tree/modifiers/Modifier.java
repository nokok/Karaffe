package org.karaffe.compiler.base.tree.modifiers;

import java.io.Serializable;

public interface Modifier extends Serializable, Comparable<Modifier> {
    public int modifierVal();
}
