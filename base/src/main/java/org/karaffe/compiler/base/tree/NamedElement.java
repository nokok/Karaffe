package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.term.Name;

public interface NamedElement {
    public void setName(Name name);

    public Name getName();
}
