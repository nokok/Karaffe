package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.type.Type;

public interface TypedElement {
    Type asType();

    void setType(Type type);
}
