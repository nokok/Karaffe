package org.karaffe.compiler.base.tree.modifier;

import java.io.Serializable;

public interface Modifier extends Serializable, Comparable<Modifier> {
    public int modifierVal();
}
