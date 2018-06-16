package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.term.Path;

public interface TypedElement {
    Path getTypeName();

    void setTypeName(Path type);
}
