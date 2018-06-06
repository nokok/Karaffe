package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.term.Name;

public interface NameableElement {
    public Name getName();

    public void setName(Name name);
}
