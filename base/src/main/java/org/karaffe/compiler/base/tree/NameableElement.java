package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.term.Path;

public interface NameableElement {
    Path getName();

    void setName(Path name);
}
